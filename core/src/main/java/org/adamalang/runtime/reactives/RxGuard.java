/*
* Adama Platform and Language
* Copyright (C) 2021 - 2023 by Adama Platform Initiative, LLC
* 
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Affero General Public License as published
* by the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
* 
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU Affero General Public License for more details.
* 
* You should have received a copy of the GNU Affero General Public License
* along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
package org.adamalang.runtime.reactives;

import org.adamalang.runtime.contracts.RxParent;
import org.adamalang.runtime.json.JsonStreamReader;
import org.adamalang.runtime.json.JsonStreamWriter;

import java.util.TreeMap;

/**
 * a condition to learn if changes have occured. This is like a Lazy, but gives people the ability
 * to learn if changes have happened since the last time a commited happened
 */
public class RxGuard extends RxDependent {
  protected boolean invalid;
  private int generation;
  private boolean raisingDirtyParent;
  private TreeMap<Integer, Integer> bumps;

  public RxGuard(RxParent parent) {
    super(parent);
    generation = 0;
    invalid = true;
    raisingDirtyParent = false;
    bumps = null;
  }

  @Override
  public boolean alive() {
    if (__parent != null) {
      return __parent.__isAlive();
    }
    return true;
  }

  @Override
  public void __commit(String name, JsonStreamWriter forwardDelta, JsonStreamWriter reverseDelta) {
    __revert();
  }

  @Override
  public void __dump(final JsonStreamWriter writer) {
  }

  @Override
  public void __insert(final JsonStreamReader reader) {
  }

  @Override
  public void __patch(JsonStreamReader reader) {
  }

  private void inc() {
    if (__parent instanceof RxRecordBase && generation == 0) {
      generation = ((RxRecordBase) __parent).__id();
    }
    generation *= 65521;
    generation++;
  }

  @Override
  public void __revert() {
    if (invalid) {
      inc();
      invalid = false;
    }
  }

  @Override
  public boolean __raiseInvalid() {
    if (__parent != null) {
      if (raisingDirtyParent) {
        return true;
      } else {
        raisingDirtyParent = true;
        __parent.__raiseDirty();
        raisingDirtyParent = false;
      }
    }
    inc();
    invalid = true;
    return true;
  }

  public int getGeneration(int viewerId) {
    if (generation == 0) {
      inc();
    }
    int bump = 0;
    if (bumps != null) {
      Integer bumpTest = bumps.get(viewerId);
      if (bumpTest != null) {
        bump = bumpTest;
      }
    }
    if (isFired(viewerId)) {
      if (bumps == null) {
        bumps = new TreeMap<>();
      }
      Integer val = bumps.get(viewerId);
      if (val == null) {
        val = 0;
      }
      val += generation * 17;
      bumps.put(viewerId, val);
      bump = val;
    }
    return generation + bump;
  }
}
