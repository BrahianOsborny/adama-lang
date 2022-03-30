/*
 * This file is subject to the terms and conditions outlined in the file 'LICENSE' (hint: it's MIT); this file is located in the root directory near the README.md which you should also read.
 *
 * This file is part of the 'Adama' project which is a programming language and document store for board games; however, it can be so much more.
 *
 * See http://www.adama-lang.org/ for more information.
 *
 * (c) 2020 - 2022 by Jeffrey M. Barber (http://jeffrey.io)
 */
package org.adamalang.web.service;

import java.nio.charset.StandardCharsets;

import java.util.Base64;

public class JavaScriptClient {
  public static final byte[] ADAMA_JS_CLIENT_BYTES = Base64.getDecoder().decode("IWZ1bmN0aW9uKGUsdCl7Im9iamVjdCI9PXR5cGVvZiBleHBvcnRzJiYib2JqZWN0Ij09dHlwZW9mIG1vZHVsZT9tb2R1bGUuZXhwb3J0cz10KCk6ImZ1bmN0aW9uIj09dHlwZW9mIGRlZmluZSYmZGVmaW5lLmFtZD9kZWZpbmUoW10sdCk6Im9iamVjdCI9PXR5cGVvZiBleHBvcnRzP2V4cG9ydHMuQWRhbWE9dCgpOmUuQWRhbWE9dCgpfSh0aGlzLChmdW5jdGlvbigpe3JldHVybiBmdW5jdGlvbihlKXt2YXIgdD17fTtmdW5jdGlvbiBuKHIpe2lmKHRbcl0pcmV0dXJuIHRbcl0uZXhwb3J0czt2YXIgaT10W3JdPXtpOnIsbDohMSxleHBvcnRzOnt9fTtyZXR1cm4gZVtyXS5jYWxsKGkuZXhwb3J0cyxpLGkuZXhwb3J0cyxuKSxpLmw9ITAsaS5leHBvcnRzfXJldHVybiBuLm09ZSxuLmM9dCxuLmQ9ZnVuY3Rpb24oZSx0LHIpe24ubyhlLHQpfHxPYmplY3QuZGVmaW5lUHJvcGVydHkoZSx0LHtlbnVtZXJhYmxlOiEwLGdldDpyfSl9LG4ucj1mdW5jdGlvbihlKXsidW5kZWZpbmVkIiE9dHlwZW9mIFN5bWJvbCYmU3ltYm9sLnRvU3RyaW5nVGFnJiZPYmplY3QuZGVmaW5lUHJvcGVydHkoZSxTeW1ib2wudG9TdHJpbmdUYWcse3ZhbHVlOiJNb2R1bGUifSksT2JqZWN0LmRlZmluZVByb3BlcnR5KGUsIl9fZXNNb2R1bGUiLHt2YWx1ZTohMH0pfSxuLnQ9ZnVuY3Rpb24oZSx0KXtpZigxJnQmJihlPW4oZSkpLDgmdClyZXR1cm4gZTtpZig0JnQmJiJvYmplY3QiPT10eXBlb2YgZSYmZSYmZS5fX2VzTW9kdWxlKXJldHVybiBlO3ZhciByPU9iamVjdC5jcmVhdGUobnVsbCk7aWYobi5yKHIpLE9iamVjdC5kZWZpbmVQcm9wZXJ0eShyLCJkZWZhdWx0Iix7ZW51bWVyYWJsZTohMCx2YWx1ZTplfSksMiZ0JiYic3RyaW5nIiE9dHlwZW9mIGUpZm9yKHZhciBpIGluIGUpbi5kKHIsaSxmdW5jdGlvbih0KXtyZXR1cm4gZVt0XX0uYmluZChudWxsLGkpKTtyZXR1cm4gcn0sbi5uPWZ1bmN0aW9uKGUpe3ZhciB0PWUmJmUuX19lc01vZHVsZT9mdW5jdGlvbigpe3JldHVybiBlLmRlZmF1bHR9OmZ1bmN0aW9uKCl7cmV0dXJuIGV9O3JldHVybiBuLmQodCwiYSIsdCksdH0sbi5vPWZ1bmN0aW9uKGUsdCl7cmV0dXJuIE9iamVjdC5wcm90b3R5cGUuaGFzT3duUHJvcGVydHkuY2FsbChlLHQpfSxuLnA9IiIsbihuLnM9MSl9KFtmdW5jdGlvbihlLHQsbil7KGZ1bmN0aW9uKHQpe3ZhciBuPW51bGw7InVuZGVmaW5lZCIhPXR5cGVvZiBXZWJTb2NrZXQ/bj1XZWJTb2NrZXQ6InVuZGVmaW5lZCIhPXR5cGVvZiBNb3pXZWJTb2NrZXQ/bj1Nb3pXZWJTb2NrZXQ6dm9pZCAwIT09dD9uPXQuV2ViU29ja2V0fHx0Lk1veldlYlNvY2tldDoidW5kZWZpbmVkIiE9dHlwZW9mIHdpbmRvdz9uPXdpbmRvdy5XZWJTb2NrZXR8fHdpbmRvdy5Nb3pXZWJTb2NrZXQ6InVuZGVmaW5lZCIhPXR5cGVvZiBzZWxmJiYobj1zZWxmLldlYlNvY2tldHx8c2VsZi5Nb3pXZWJTb2NrZXQpLGUuZXhwb3J0cz1ufSkuY2FsbCh0aGlzLG4oMikpfSxmdW5jdGlvbihlLHQsbil7InVzZSBzdHJpY3QiO24ucih0KSxuLmQodCwiUHJvZHVjdGlvbiIsKGZ1bmN0aW9uKCl7cmV0dXJuIGN9KSksbi5kKHQsIlRyZWUiLChmdW5jdGlvbigpe3JldHVybiB1fSkpLG4uZCh0LCJDb25zb2xlTG9nU2VxUmVzcG9uZGVyIiwoZnVuY3Rpb24oKXtyZXR1cm4gZH0pKSxuLmQodCwiVHJlZVBpcGVEYXRhUmVzcG9uc2UiLChmdW5jdGlvbigpe3JldHVybiBhfSkpLG4uZCh0LCJDb25uZWN0aW9uIiwoZnVuY3Rpb24oKXtyZXR1cm4gaH0pKTt2YXIgcj1uKDApLGk9bi5uKHIpLG89ZnVuY3Rpb24oZSx0LG4scil7cmV0dXJuIG5ldyhufHwobj1Qcm9taXNlKSkoKGZ1bmN0aW9uKGksbyl7ZnVuY3Rpb24gcyhlKXt0cnl7dShyLm5leHQoZSkpfWNhdGNoKGUpe28oZSl9fWZ1bmN0aW9uIGMoZSl7dHJ5e3Uoci50aHJvdyhlKSl9Y2F0Y2goZSl7byhlKX19ZnVuY3Rpb24gdShlKXt2YXIgdDtlLmRvbmU/aShlLnZhbHVlKToodD1lLnZhbHVlLHQgaW5zdGFuY2VvZiBuP3Q6bmV3IG4oKGZ1bmN0aW9uKGUpe2UodCl9KSkpLnRoZW4ocyxjKX11KChyPXIuYXBwbHkoZSx0fHxbXSkpLm5leHQoKSl9KSl9LHM9ZnVuY3Rpb24oZSx0KXt2YXIgbixyLGksbyxzPXtsYWJlbDowLHNlbnQ6ZnVuY3Rpb24oKXtpZigxJmlbMF0pdGhyb3cgaVsxXTtyZXR1cm4gaVsxXX0sdHJ5czpbXSxvcHM6W119O3JldHVybiBvPXtuZXh0OmMoMCksdGhyb3c6YygxKSxyZXR1cm46YygyKX0sImZ1bmN0aW9uIj09dHlwZW9mIFN5bWJvbCYmKG9bU3ltYm9sLml0ZXJhdG9yXT1mdW5jdGlvbigpe3JldHVybiB0aGlzfSksbztmdW5jdGlvbiBjKG8pe3JldHVybiBmdW5jdGlvbihjKXtyZXR1cm4gZnVuY3Rpb24obyl7aWYobil0aHJvdyBuZXcgVHlwZUVycm9yKCJHZW5lcmF0b3IgaXMgYWxyZWFkeSBleGVjdXRpbmcuIik7Zm9yKDtzOyl0cnl7aWYobj0xLHImJihpPTImb1swXT9yLnJldHVybjpvWzBdP3IudGhyb3d8fCgoaT1yLnJldHVybikmJmkuY2FsbChyKSwwKTpyLm5leHQpJiYhKGk9aS5jYWxsKHIsb1sxXSkpLmRvbmUpcmV0dXJuIGk7c3dpdGNoKHI9MCxpJiYobz1bMiZvWzBdLGkudmFsdWVdKSxvWzBdKXtjYXNlIDA6Y2FzZSAxOmk9bzticmVhaztjYXNlIDQ6cmV0dXJuIHMubGFiZWwrKyx7dmFsdWU6b1sxXSxkb25lOiExfTtjYXNlIDU6cy5sYWJlbCsrLHI9b1sxXSxvPVswXTtjb250aW51ZTtjYXNlIDc6bz1zLm9wcy5wb3AoKSxzLnRyeXMucG9wKCk7Y29udGludWU7ZGVmYXVsdDppZighKGk9cy50cnlzLChpPWkubGVuZ3RoPjAmJmlbaS5sZW5ndGgtMV0pfHw2IT09b1swXSYmMiE9PW9bMF0pKXtzPTA7Y29udGludWV9aWYoMz09PW9bMF0mJighaXx8b1sxXT5pWzBdJiZvWzFdPGlbM10pKXtzLmxhYmVsPW9bMV07YnJlYWt9aWYoNj09PW9bMF0mJnMubGFiZWw8aVsxXSl7cy5sYWJlbD1pWzFdLGk9bzticmVha31pZihpJiZzLmxhYmVsPGlbMl0pe3MubGFiZWw9aVsyXSxzLm9wcy5wdXNoKG8pO2JyZWFrfWlbMl0mJnMub3BzLnBvcCgpLHMudHJ5cy5wb3AoKTtjb250aW51ZX1vPXQuY2FsbChlLHMpfWNhdGNoKGUpe289WzYsZV0scj0wfWZpbmFsbHl7bj1pPTB9aWYoNSZvWzBdKXRocm93IG9bMV07cmV0dXJue3ZhbHVlOm9bMF0/b1sxXTp2b2lkIDAsZG9uZTohMH19KFtvLGNdKX19fSxjPSJhd3MtdXMtZWFzdC0yLmFkYW1hLXBsYXRmb3JtLmNvbSIsdT1mdW5jdGlvbigpe2Z1bmN0aW9uIGUoKXt0aGlzLnRyZWU9e30sdGhpcy5kaXNwYXRjaD17fSx0aGlzLmRpc3BhdGNoX2NvdW50PTAsdGhpcy5xdWV1ZT1bXSx0aGlzLm9uZGVjaWRlPWZ1bmN0aW9uKGUpe319cmV0dXJuIGUucHJvdG90eXBlLl9fcmVjQXBwZW5kQ2hhbmdlPWZ1bmN0aW9uKGUsdCxuKXtpZigib2JqZWN0Ij09dHlwZW9mIHQpZm9yKHZhciByIGluIHQpciBpbiBlfHwoZVtyXT17fSksdGhpcy5fX3JlY0FwcGVuZENoYW5nZShlW3JdLHRbcl0sbik7ZWxzZSJmdW5jdGlvbiI9PXR5cGVvZiB0JiYoIkBlImluIGV8fChlWyJAZSJdPVtdKSxlWyJAZSJdLnB1c2goe2NiOnQsb3JkZXI6bn0pKX0sZS5wcm90b3R5cGUub25UcmVlQ2hhbmdlPWZ1bmN0aW9uKGUpe3RoaXMuX19yZWNBcHBlbmRDaGFuZ2UodGhpcy5kaXNwYXRjaCxlLHRoaXMuZGlzcGF0Y2hfY291bnQpLHRoaXMuZGlzcGF0Y2hfY291bnQrK30sZS5wcm90b3R5cGUubWVyZ2VVcGRhdGU9ZnVuY3Rpb24oZSl7ImRhdGEiaW4gZSYmdGhpcy5fX3JlY01lcmdlQW5kRGlzcGF0Y2godGhpcy50cmVlLHRoaXMuZGlzcGF0Y2gsZS5kYXRhKSwib3V0c3RhbmRpbmciaW4gZSYmdGhpcy5vbmRlY2lkZShlLm91dHN0YW5kaW5nKSx0aGlzLl9fZHJhaW4oKX0sZS5wcm90b3R5cGUuX19yZWNEZWxldGVBbmREaXNwYXRjaD1mdW5jdGlvbihlLHQpe2Zvcih2YXIgbiBpbiBlKXt2YXIgcj1lW25dO0FycmF5LmlzQXJyYXkocil8fG4gaW4gdCYmdGhpcy5fX3JlY0RlbGV0ZUFuZERpc3BhdGNoKHIsdFtuXSk7dmFyIGk9Ii0iK247bnVsbCE9dCYmaSBpbiB0JiZ0aGlzLl9fZmlyZSh0W2ldLHtrZXk6bixiZWZvcmU6cix2YWx1ZTpudWxsfSl9fSxlLnByb3RvdHlwZS5fX3JlY01lcmdlQW5kRGlzcGF0Y2g9ZnVuY3Rpb24oZSx0LG4pe2Zvcih2YXIgciBpbiBuKXt2YXIgaT1uW3JdO2lmKG51bGwhPT1pKXt2YXIgbz0hKHIgaW4gZSk7aWYoIm9iamVjdCI9PXR5cGVvZiBpKXt2YXIgcz0iQG8iaW4gaXx8IkBzImluIGk7ciBpbiBlfHwocz8oZVtyXT1bXSxlWyIjIityXT17fSk6ZVtyXT17fSksKHM9QXJyYXkuaXNBcnJheShlW3JdKXx8cyk/dGhpcy5fX3JlY01lcmdlQW5kRGlzcGF0Y2hBcnJheShlW3JdLG51bGwhPXQmJnIgaW4gdD90W3JdOm51bGwsZVsiIyIrcl0saSk6dGhpcy5fX3JlY01lcmdlQW5kRGlzcGF0Y2goZVtyXSxudWxsIT10JiZyIGluIHQ/dFtyXTpudWxsLGkpfWVsc2V7dmFyIGM9ciBpbiBlP2Vbcl06bnVsbDtlW3JdPWksbnVsbCE9dCYmciBpbiB0JiZ0aGlzLl9fZmlyZSh0W3JdLHtrZXk6cixiZWZvcmU6Yyx2YWx1ZTppfSl9aWYobyl7dmFyIHU9IisiK3I7bnVsbCE9dCYmdSBpbiB0JiZ0aGlzLl9fZmlyZSh0W3VdLHtrZXk6cix2YWx1ZTplW3JdfSl9bnVsbCE9dCYmIkBlImluIHQmJnRoaXMuX19maXJlKHQse3ZhbHVlOmV9KX1lbHNle3ZhciBkPSItIityLGE9ZVtyXTtpZihudWxsIT10JiZkIGluIHQmJnRoaXMuX19maXJlKHRbZF0se2tleTpyLGJlZm9yZTphLHZhbHVlOm51bGx9KSxBcnJheS5pc0FycmF5KGEpKXt2YXIgaD0iIyIrcjtoIGluIGUmJmggaW4gdCYmdGhpcy5fX3JlY0RlbGV0ZUFuZERpc3BhdGNoKGVbaF0sdFtoXSksZGVsZXRlIGVbIiMiK3JdfXIgaW4gZSYmciBpbiB0JiZ0aGlzLl9fcmVjRGVsZXRlQW5kRGlzcGF0Y2goZVtyXSx0W3JdKSxkZWxldGUgZVtyXSxudWxsIT10JiZyIGluIHQmJnRoaXMuX19maXJlKHRbcl0se2tleTpyLGJlZm9yZTphLHZhbHVlOm51bGx9KX19fSxlLnByb3RvdHlwZS5fX3JlY01lcmdlQW5kRGlzcGF0Y2hBcnJheT1mdW5jdGlvbihlLHQsbixyKXt2YXIgaT1udWxsLG89bnVsbDtmb3IodmFyIHMgaW4gcilpZigiQG8iPT1zKWk9cltzXTtlbHNlIGlmKCJAcyI9PXMpbz1yW3NdO2Vsc2UgaWYobnVsbD09cltzXSl0JiYiLSJpbiB0JiZ0aGlzLl9fZmlyZSh0WyItIl0se2tleTpzLGJlZm9yZTpuW3NdLHZhbHVlOm51bGx9KSxkZWxldGUgbltzXTtlbHNle3ZhciBjPSExO251bGwhPW4mJnMgaW4gbnx8KHQmJiIrImluIHQmJihjPSEwKSxuW3NdPXt9KSx0aGlzLl9fcmVjTWVyZ2VBbmREaXNwYXRjaChuW3NdLG51bGwhPXQmJiIjImluIHQ/dFsiIyJdOm51bGwscltzXSksYyYmdGhpcy5fX2ZpcmUodFsiKyJdLHtrZXk6cyxiZWZvcmU6bnVsbCx2YWx1ZTpuW3NdfSl9dmFyIHU9e2JlZm9yZTplLHZhbHVlOmV9O2lmKG51bGwhPT1vKXt1LmJlZm9yZT1bXTtmb3IodmFyIGQ9MDtkPGUubGVuZ3RoO2QrKyl1LmJlZm9yZS5wdXNoKGVbZF0pO2UubGVuZ3RoPW99aWYobnVsbCE9PWkpe3ZhciBhPVtdO3UuYmVmb3JlPVtdO3ZhciBoPSEhdCYmIi0iaW4gdDtmb3IoZD0wO2Q8ZS5sZW5ndGg7ZCsrKXUuYmVmb3JlLnB1c2goZVtkXSksaCYmKGVbZF0uX19raWxsPSEwKTtmb3IoZD0wO2Q8aS5sZW5ndGg7ZCsrKXt2YXIgcD1pW2RdLGY9dHlwZW9mIHA7aWYoInN0cmluZyI9PWZ8fCJudW1iZXIiPT1mKWEucHVzaChuW3BdKSxoJiYobltwXS5fX2tpbGw9ITEpO2Vsc2UgZm9yKHZhciBsPXBbMF0sXz1wWzFdLHk9bDt5PD1fO3krKyloJiYoZVt5XS5fX2tpbGw9ITEpLGEucHVzaChlW3ldKX1pZihoKWZvcihzIGluIG4pbltzXS5fX2tpbGwmJihzIGluIHQmJnRoaXMuX19yZWNEZWxldGVBbmREaXNwYXRjaChuW3NdLHRbc10pLHRoaXMuX19maXJlKHRbIi0iXSx7a2V5OnMsYmVmb3JlOm5bc10sdmFsdWU6bnVsbH0pKSxkZWxldGUgbltzXS5fX2tpbGw7ZS5sZW5ndGg9YS5sZW5ndGg7Zm9yKGQ9MDtkPGEubGVuZ3RoO2QrKyllW2RdPWFbZF19dGhpcy5fX2ZpcmUodCx1KX0sZS5wcm90b3R5cGUuX19maXJlPWZ1bmN0aW9uKGUsdCl7aWYoZSYmIkBlImluIGUpe2Zvcih2YXIgbj1lWyJAZSJdLHI9MCxpPTA7aTxuLmxlbmd0aDtpKyspe3ZhciBvPW5baV07bnVsbCE9PW8/dGhpcy5xdWV1ZS5wdXNoKHtjYjpvLmNiLG9yZGVyOm8ub3JkZXIsY2hhbmdlOnQsZGlzcGF0Y2hfbGlzdDpuLGluZGV4Oml9KTpyKyt9aWYocj4wKXt2YXIgcz1bXTtmb3IoaT0wO2k8bi5sZW5ndGg7aSsrKW51bGwhPT1vJiZzLnB1c2gobyk7ZVsiQGUiXT1zfX19LGUucHJvdG90eXBlLl9fZHJhaW49ZnVuY3Rpb24oKXt0aGlzLnF1ZXVlLnNvcnQoKGZ1bmN0aW9uKGUsdCl7cmV0dXJuIGUub3JkZXItdC5vcmRlcn0pKTtmb3IodmFyIGU9MDtlPHRoaXMucXVldWUubGVuZ3RoO2UrKyl7dmFyIHQ9dGhpcy5xdWV1ZVtlXTsiZGVsZXRlIj09PXQuY2IodC5jaGFuZ2UpJiYodC5kaXNwYXRjaF9saXN0W3QuaW5kZXhdPW51bGwpfXRoaXMucXVldWU9W119LGV9KCksZD1mdW5jdGlvbigpe2Z1bmN0aW9uIGUoZSl7dGhpcy5wcmVmaXg9ZX1yZXR1cm4gZS5wcm90b3R5cGUuZmFpbHVyZT1mdW5jdGlvbihlKXtjb25zb2xlLmxvZyh0aGlzLnByZWZpeCsifGVycm9yIitlKX0sZS5wcm90b3R5cGUuc3VjY2Vzcz1mdW5jdGlvbihlKXtjb25zb2xlLmxvZyh0aGlzLnByZWZpeCsifHN1Y2Nlc3M7c2VxPSIrZS5zZXEpfSxlfSgpLGE9ZnVuY3Rpb24oKXtmdW5jdGlvbiBlKGUpe3RoaXMudHJlZT1lfXJldHVybiBlLnByb3RvdHlwZS5uZXh0PWZ1bmN0aW9uKGUpe3RoaXMudHJlZS5tZXJnZVVwZGF0ZShlLmRlbHRhKX0sZS5wcm90b3R5cGUuZmFpbHVyZT1mdW5jdGlvbihlKXtjb25zb2xlLmxvZygidHJlZXxmYWlsdXJlPSIrZSl9LGUucHJvdG90eXBlLmNvbXBsZXRlPWZ1bmN0aW9uKCl7fSxlfSgpLGg9ZnVuY3Rpb24oKXtmdW5jdGlvbiBlKGUpe3RoaXMuYmFja29mZj0xLHRoaXMuaG9zdD1lLHRoaXMudXJsPSJ3c3M6Ly8iK2UrIi8iLHRoaXMuYXNzZXRzPSEwLHRoaXMuY29ubmVjdGVkPSExLHRoaXMuZGVhZD0hMSx0aGlzLm1heGltdW1fYmFja29mZj0yNTAwLHRoaXMuc29ja2V0PW51bGwsdGhpcy5vbnN0YXR1c2NoYW5nZT1mdW5jdGlvbihlKXt9LHRoaXMub25waW5nPWZ1bmN0aW9uKGUsdCl7fSx0aGlzLm9uYXV0aG5lZWRlZD1mdW5jdGlvbihlKXt9LHRoaXMuc2NoZWR1bGVkPSExLHRoaXMuY2FsbGJhY2tzPW5ldyBNYXAsdGhpcy5uZXh0SWQ9MCx0aGlzLm9ucmVjb25uZWN0PW5ldyBNYXAsdGhpcy5ycGNpZD0xLHRoaXMuc2Vzc2lvbklkPSIiLHRoaXMuc2VuZElkPTB9cmV0dXJuIGUucHJvdG90eXBlLnN0b3A9ZnVuY3Rpb24oKXt0aGlzLmRlYWQ9ITAsbnVsbCE9PXRoaXMuc29ja2V0JiZ0aGlzLnNvY2tldC5jbG9zZSgpfSxlLnByb3RvdHlwZS5fcmV0cnk9ZnVuY3Rpb24oKXtpZih0aGlzLnNvY2tldD1udWxsLHRoaXMuY29ubmVjdGVkJiYodGhpcy5jb25uZWN0ZWQ9ITEsdGhpcy5vbnN0YXR1c2NoYW5nZSghMSkpLHRoaXMuY2FsbGJhY2tzLmNsZWFyKCksIXRoaXMuZGVhZCYmIXRoaXMuc2NoZWR1bGVkKXt2YXIgZT0hMTt0aGlzLmJhY2tvZmYrPU1hdGgucmFuZG9tKCkqdGhpcy5iYWNrb2ZmLHRoaXMuYmFja29mZj50aGlzLm1heGltdW1fYmFja29mZiYmKHRoaXMuYmFja29mZj10aGlzLm1heGltdW1fYmFja29mZixlPSEwKSx0aGlzLnNjaGVkdWxlZD0hMDt2YXIgdD10aGlzO3NldFRpbWVvdXQoKGZ1bmN0aW9uKCl7dC5zdGFydCgpfSksdGhpcy5iYWNrb2ZmKSxlJiYodGhpcy5iYWNrb2ZmLz0yKX19LGUucHJvdG90eXBlLnN0YXJ0PWZ1bmN0aW9uKCl7dmFyIGU9dGhpczt0aGlzLnNjaGVkdWxlZD0hMSx0aGlzLmRlYWQ9ITEsdGhpcy5zb2NrZXQ9bmV3IGkuYSh0aGlzLnVybCksdGhpcy5zb2NrZXQub25tZXNzYWdlPWZ1bmN0aW9uKHQpe3ZhciBuPUpTT04ucGFyc2UodC5kYXRhKTtpZigicGluZyJpbiBuKXJldHVybiBlLm9ucGluZyhuLnBpbmcsbi5sYXRlbmN5KSxuLnBvbmc9KG5ldyBEYXRlKS5nZXRUaW1lKCkvMWUzLHZvaWQgZS5zb2NrZXQuc2VuZChKU09OLnN0cmluZ2lmeShuKSk7aWYoInN0YXR1cyJpbiBuKXJldHVybiJjb25uZWN0ZWQiIT1uLnN0YXR1cz8oZS5kZWFkPSEwLGUuc29ja2V0LmNsb3NlKCksZS5zb2NrZXQ9bnVsbCx2b2lkIGUub25hdXRobmVlZGVkKChmdW5jdGlvbigpe2Uuc3RhcnQoKX0pKSk6KGUuYmFja29mZj0xLGUuY29ubmVjdGVkPSEwLGUuYXNzZXRzPW4uYXNzZXRzLGUuc2Vzc2lvbklkPW4uc2Vzc2lvbl9pZCxlLm9uc3RhdHVzY2hhbmdlKCEwKSx2b2lkIGUuX3JlY29ubmVjdCgpKTtpZigiZmFpbHVyZSJpbiBuKWUuY2FsbGJhY2tzLmhhcyhuLmZhaWx1cmUpJiYocj1lLmNhbGxiYWNrcy5nZXQobi5mYWlsdXJlKSkmJihlLmNhbGxiYWNrcy5kZWxldGUobi5mYWlsdXJlKSxyKG4pKTtlbHNlIGlmKCJkZWxpdmVyImluIG4pe3ZhciByO2lmKGUuY2FsbGJhY2tzLmhhcyhuLmRlbGl2ZXIpKShyPWUuY2FsbGJhY2tzLmdldChuLmRlbGl2ZXIpKSYmKG4uZG9uZSYmZS5jYWxsYmFja3MuZGVsZXRlKG4uZGVsaXZlcikscihuKSl9fSx0aGlzLnNvY2tldC5vbmNsb3NlPWZ1bmN0aW9uKHQpe2UuX3JldHJ5KCl9LHRoaXMuc29ja2V0Lm9uZXJyb3I9ZnVuY3Rpb24odCl7ZS5fcmV0cnkoKX19LGUucHJvdG90eXBlLl93cml0ZT1mdW5jdGlvbihlLHQpe2lmKHRoaXMuY29ubmVjdGVkKXt2YXIgbj10aGlzLnJwY2lkO3RoaXMucnBjaWQrKyxlLmlkPW4sdGhpcy5jYWxsYmFja3Muc2V0KG4sdCksdGhpcy5zb2NrZXQuc2VuZChKU09OLnN0cmluZ2lmeShlKSl9ZWxzZSB0KHtmYWlsdXJlOjYwMCxyZWFzb246OTk5OX0pfSxlLnByb3RvdHlwZS53YWl0X2Nvbm5lY3RlZD1mdW5jdGlvbigpe3JldHVybiBvKHRoaXMsdm9pZCAwLHZvaWQgMCwoZnVuY3Rpb24oKXt2YXIgZSx0O3JldHVybiBzKHRoaXMsKGZ1bmN0aW9uKG4pe3JldHVybiB0aGlzLmNvbm5lY3RlZD9bMixuZXcgUHJvbWlzZSgoZnVuY3Rpb24oZSl7ZSghMCl9KSldOihlPXRoaXMsdD10aGlzLm9uc3RhdHVzY2hhbmdlLFsyLG5ldyBQcm9taXNlKChmdW5jdGlvbihuKXtlLm9uc3RhdHVzY2hhbmdlPWZ1bmN0aW9uKHIpe3QociksciYmKG4oITApLGUub25zdGF0dXNjaGFuZ2U9dCl9fSkpXSl9KSl9KSl9LGUucHJvdG90eXBlLl9yZWNvbm5lY3Q9ZnVuY3Rpb24oKXt0aGlzLm9ucmVjb25uZWN0LmZvckVhY2goKGZ1bmN0aW9uKGUsdCl7ZS5fX3JldHJ5KCl9KSl9LGUucHJvdG90eXBlLl9fZXhlY3V0ZV9ycj1mdW5jdGlvbihlKXt2YXIgdD10aGlzO3JldHVybiBlLmZpcnN0PSEwLHQuX3dyaXRlKGUucmVxdWVzdCwoZnVuY3Rpb24obil7ZS5maXJzdCYmKGUuZmlyc3Q9ITEsImZhaWx1cmUiaW4gbj9lLnJlc3BvbmRlci5mYWlsdXJlKG4ucmVhc29uKTplLnJlc3BvbmRlci5zdWNjZXNzKG4ucmVzcG9uc2UpKSx0Lm9ucmVjb25uZWN0LmRlbGV0ZShlLmlkKX0pKSx0Lm9ucmVjb25uZWN0LnNldChlLmlkLGUpLGUuX19yZXRyeT1mdW5jdGlvbigpe3QuX19leGVjdXRlX3JyKGUpfSxlfSxlLnByb3RvdHlwZS5fX2V4ZWN1dGVfc3RyZWFtPWZ1bmN0aW9uKGUpe3ZhciB0PXRoaXM7cmV0dXJuIHQuX3dyaXRlKGUucmVxdWVzdCwoZnVuY3Rpb24obil7aWYoImZhaWx1cmUiaW4gbilyZXR1cm4gZS5yZXNwb25kZXIuZmFpbHVyZShuLnJlYXNvbiksdm9pZCB0Lm9ucmVjb25uZWN0LmRlbGV0ZShlLmlkKTtuLnJlc3BvbnNlJiZlLnJlc3BvbmRlci5uZXh0KG4ucmVzcG9uc2UpLG4uZG9uZSYmKGUucmVzcG9uZGVyLmNvbXBsZXRlKCksdC5vbnJlY29ubmVjdC5kZWxldGUoZS5pZCkpfSkpLHQub25yZWNvbm5lY3Quc2V0KGUuaWQsZSksZS5fX3JldHJ5PWZ1bmN0aW9uKCl7dC5fX2V4ZWN1dGVfc3RyZWFtKGUpfSxlfSxlLnByb3RvdHlwZS5Jbml0U2V0dXBBY2NvdW50PWZ1bmN0aW9uKGUsdCl7dGhpcy5uZXh0SWQrKzt2YXIgbj10aGlzLm5leHRJZDtyZXR1cm4gdGhpcy5fX2V4ZWN1dGVfcnIoe2lkOm4scmVzcG9uZGVyOnQscmVxdWVzdDp7bWV0aG9kOiJpbml0L3NldHVwLWFjY291bnQiLGlkOm4sZW1haWw6ZX19KX0sZS5wcm90b3R5cGUuSW5pdENvbXBsZXRlQWNjb3VudD1mdW5jdGlvbihlLHQsbixyKXt0aGlzLm5leHRJZCsrO3ZhciBpPXRoaXMubmV4dElkO3JldHVybiB0aGlzLl9fZXhlY3V0ZV9ycih7aWQ6aSxyZXNwb25kZXI6cixyZXF1ZXN0OnttZXRob2Q6ImluaXQvY29tcGxldGUtYWNjb3VudCIsaWQ6aSxlbWFpbDplLHJldm9rZTp0LGNvZGU6bn19KX0sZS5wcm90b3R5cGUuQWNjb3VudFNldFBhc3N3b3JkPWZ1bmN0aW9uKGUsdCxuKXt0aGlzLm5leHRJZCsrO3ZhciByPXRoaXMubmV4dElkO3JldHVybiB0aGlzLl9fZXhlY3V0ZV9ycih7aWQ6cixyZXNwb25kZXI6bixyZXF1ZXN0OnttZXRob2Q6ImFjY291bnQvc2V0LXBhc3N3b3JkIixpZDpyLGlkZW50aXR5OmUscGFzc3dvcmQ6dH19KX0sZS5wcm90b3R5cGUuQWNjb3VudExvZ2luPWZ1bmN0aW9uKGUsdCxuKXt0aGlzLm5leHRJZCsrO3ZhciByPXRoaXMubmV4dElkO3JldHVybiB0aGlzLl9fZXhlY3V0ZV9ycih7aWQ6cixyZXNwb25kZXI6bixyZXF1ZXN0OnttZXRob2Q6ImFjY291bnQvbG9naW4iLGlkOnIsZW1haWw6ZSxwYXNzd29yZDp0fX0pfSxlLnByb3RvdHlwZS5Qcm9iZT1mdW5jdGlvbihlLHQpe3RoaXMubmV4dElkKys7dmFyIG49dGhpcy5uZXh0SWQ7cmV0dXJuIHRoaXMuX19leGVjdXRlX3JyKHtpZDpuLHJlc3BvbmRlcjp0LHJlcXVlc3Q6e21ldGhvZDoicHJvYmUiLGlkOm4saWRlbnRpdHk6ZX19KX0sZS5wcm90b3R5cGUuQXV0aG9yaXR5Q3JlYXRlPWZ1bmN0aW9uKGUsdCl7dGhpcy5uZXh0SWQrKzt2YXIgbj10aGlzLm5leHRJZDtyZXR1cm4gdGhpcy5fX2V4ZWN1dGVfcnIoe2lkOm4scmVzcG9uZGVyOnQscmVxdWVzdDp7bWV0aG9kOiJhdXRob3JpdHkvY3JlYXRlIixpZDpuLGlkZW50aXR5OmV9fSl9LGUucHJvdG90eXBlLkF1dGhvcml0eVNldD1mdW5jdGlvbihlLHQsbixyKXt0aGlzLm5leHRJZCsrO3ZhciBpPXRoaXMubmV4dElkO3JldHVybiB0aGlzLl9fZXhlY3V0ZV9ycih7aWQ6aSxyZXNwb25kZXI6cixyZXF1ZXN0OnttZXRob2Q6ImF1dGhvcml0eS9zZXQiLGlkOmksaWRlbnRpdHk6ZSxhdXRob3JpdHk6dCwia2V5LXN0b3JlIjpufX0pfSxlLnByb3RvdHlwZS5BdXRob3JpdHlHZXQ9ZnVuY3Rpb24oZSx0LG4pe3RoaXMubmV4dElkKys7dmFyIHI9dGhpcy5uZXh0SWQ7cmV0dXJuIHRoaXMuX19leGVjdXRlX3JyKHtpZDpyLHJlc3BvbmRlcjpuLHJlcXVlc3Q6e21ldGhvZDoiYXV0aG9yaXR5L2dldCIsaWQ6cixpZGVudGl0eTplLGF1dGhvcml0eTp0fX0pfSxlLnByb3RvdHlwZS5BdXRob3JpdHlMaXN0PWZ1bmN0aW9uKGUsdCl7dGhpcy5uZXh0SWQrKzt2YXIgbj10aGlzLm5leHRJZDtyZXR1cm4gdGhpcy5fX2V4ZWN1dGVfc3RyZWFtKHtpZDpuLHJlc3BvbmRlcjp0LHJlcXVlc3Q6e21ldGhvZDoiYXV0aG9yaXR5L2xpc3QiLGlkOm4saWRlbnRpdHk6ZX19KX0sZS5wcm90b3R5cGUuQXV0aG9yaXR5RGVzdHJveT1mdW5jdGlvbihlLHQsbil7dGhpcy5uZXh0SWQrKzt2YXIgcj10aGlzLm5leHRJZDtyZXR1cm4gdGhpcy5fX2V4ZWN1dGVfcnIoe2lkOnIscmVzcG9uZGVyOm4scmVxdWVzdDp7bWV0aG9kOiJhdXRob3JpdHkvZGVzdHJveSIsaWQ6cixpZGVudGl0eTplLGF1dGhvcml0eTp0fX0pfSxlLnByb3RvdHlwZS5TcGFjZUNyZWF0ZT1mdW5jdGlvbihlLHQsbil7dGhpcy5uZXh0SWQrKzt2YXIgcj10aGlzLm5leHRJZDtyZXR1cm4gdGhpcy5fX2V4ZWN1dGVfcnIoe2lkOnIscmVzcG9uZGVyOm4scmVxdWVzdDp7bWV0aG9kOiJzcGFjZS9jcmVhdGUiLGlkOnIsaWRlbnRpdHk6ZSxzcGFjZTp0fX0pfSxlLnByb3RvdHlwZS5TcGFjZVVzYWdlPWZ1bmN0aW9uKGUsdCxuLHIpe3RoaXMubmV4dElkKys7dmFyIGk9dGhpcy5uZXh0SWQ7cmV0dXJuIHRoaXMuX19leGVjdXRlX3N0cmVhbSh7aWQ6aSxyZXNwb25kZXI6cixyZXF1ZXN0OnttZXRob2Q6InNwYWNlL3VzYWdlIixpZDppLGlkZW50aXR5OmUsc3BhY2U6dCxsaW1pdDpufX0pfSxlLnByb3RvdHlwZS5TcGFjZUdldD1mdW5jdGlvbihlLHQsbil7dGhpcy5uZXh0SWQrKzt2YXIgcj10aGlzLm5leHRJZDtyZXR1cm4gdGhpcy5fX2V4ZWN1dGVfcnIoe2lkOnIscmVzcG9uZGVyOm4scmVxdWVzdDp7bWV0aG9kOiJzcGFjZS9nZXQiLGlkOnIsaWRlbnRpdHk6ZSxzcGFjZTp0fX0pfSxlLnByb3RvdHlwZS5TcGFjZVNldD1mdW5jdGlvbihlLHQsbixyKXt0aGlzLm5leHRJZCsrO3ZhciBpPXRoaXMubmV4dElkO3JldHVybiB0aGlzLl9fZXhlY3V0ZV9ycih7aWQ6aSxyZXNwb25kZXI6cixyZXF1ZXN0OnttZXRob2Q6InNwYWNlL3NldCIsaWQ6aSxpZGVudGl0eTplLHNwYWNlOnQscGxhbjpufX0pfSxlLnByb3RvdHlwZS5TcGFjZURlbGV0ZT1mdW5jdGlvbihlLHQsbil7dGhpcy5uZXh0SWQrKzt2YXIgcj10aGlzLm5leHRJZDtyZXR1cm4gdGhpcy5fX2V4ZWN1dGVfcnIoe2lkOnIscmVzcG9uZGVyOm4scmVxdWVzdDp7bWV0aG9kOiJzcGFjZS9kZWxldGUiLGlkOnIsaWRlbnRpdHk6ZSxzcGFjZTp0fX0pfSxlLnByb3RvdHlwZS5TcGFjZVNldFJvbGU9ZnVuY3Rpb24oZSx0LG4scixpKXt0aGlzLm5leHRJZCsrO3ZhciBvPXRoaXMubmV4dElkO3JldHVybiB0aGlzLl9fZXhlY3V0ZV9ycih7aWQ6byxyZXNwb25kZXI6aSxyZXF1ZXN0OnttZXRob2Q6InNwYWNlL3NldC1yb2xlIixpZDpvLGlkZW50aXR5OmUsc3BhY2U6dCxlbWFpbDpuLHJvbGU6cn19KX0sZS5wcm90b3R5cGUuU3BhY2VSZWZsZWN0PWZ1bmN0aW9uKGUsdCxuLHIpe3RoaXMubmV4dElkKys7dmFyIGk9dGhpcy5uZXh0SWQ7cmV0dXJuIHRoaXMuX19leGVjdXRlX3JyKHtpZDppLHJlc3BvbmRlcjpyLHJlcXVlc3Q6e21ldGhvZDoic3BhY2UvcmVmbGVjdCIsaWQ6aSxpZGVudGl0eTplLHNwYWNlOnQsa2V5Om59fSl9LGUucHJvdG90eXBlLlNwYWNlTGlzdD1mdW5jdGlvbihlLHQsbixyKXt0aGlzLm5leHRJZCsrO3ZhciBpPXRoaXMubmV4dElkO3JldHVybiB0aGlzLl9fZXhlY3V0ZV9zdHJlYW0oe2lkOmkscmVzcG9uZGVyOnIscmVxdWVzdDp7bWV0aG9kOiJzcGFjZS9saXN0IixpZDppLGlkZW50aXR5OmUsbWFya2VyOnQsbGltaXQ6bn19KX0sZS5wcm90b3R5cGUuRG9jdW1lbnRDcmVhdGU9ZnVuY3Rpb24oZSx0LG4scixpLG8pe3RoaXMubmV4dElkKys7dmFyIHM9dGhpcy5uZXh0SWQ7cmV0dXJuIHRoaXMuX19leGVjdXRlX3JyKHtpZDpzLHJlc3BvbmRlcjpvLHJlcXVlc3Q6e21ldGhvZDoiZG9jdW1lbnQvY3JlYXRlIixpZDpzLGlkZW50aXR5OmUsc3BhY2U6dCxrZXk6bixlbnRyb3B5OnIsYXJnOml9fSl9LGUucHJvdG90eXBlLkRvY3VtZW50TGlzdD1mdW5jdGlvbihlLHQsbixyLGkpe3RoaXMubmV4dElkKys7dmFyIG89dGhpcy5uZXh0SWQ7cmV0dXJuIHRoaXMuX19leGVjdXRlX3N0cmVhbSh7aWQ6byxyZXNwb25kZXI6aSxyZXF1ZXN0OnttZXRob2Q6ImRvY3VtZW50L2xpc3QiLGlkOm8saWRlbnRpdHk6ZSxzcGFjZTp0LG1hcmtlcjpuLGxpbWl0OnJ9fSl9LGUucHJvdG90eXBlLkNvbm5lY3Rpb25DcmVhdGU9ZnVuY3Rpb24oZSx0LG4scixpKXt2YXIgbz10aGlzO28ubmV4dElkKys7dmFyIHM9by5uZXh0SWQ7cmV0dXJuIG8uX19leGVjdXRlX3N0cmVhbSh7aWQ6cyxyZXNwb25kZXI6aSxyZXF1ZXN0OnttZXRob2Q6ImNvbm5lY3Rpb24vY3JlYXRlIixpZDpzLGlkZW50aXR5OmUsc3BhY2U6dCxrZXk6biwidmlld2VyLXN0YXRlIjpyfSxzZW5kOmZ1bmN0aW9uKGUsdCxuKXtvLm5leHRJZCsrO3ZhciByPW8ubmV4dElkLGk9cztvLl9fZXhlY3V0ZV9ycih7aWQ6cixyZXNwb25kZXI6bixyZXF1ZXN0OnttZXRob2Q6ImNvbm5lY3Rpb24vc2VuZCIsaWQ6cixjb25uZWN0aW9uOmksY2hhbm5lbDplLG1lc3NhZ2U6dH19KX0sdXBkYXRlOmZ1bmN0aW9uKGUsdCl7by5uZXh0SWQrKzt2YXIgbj1vLm5leHRJZCxyPXM7by5fX2V4ZWN1dGVfcnIoe2lkOm4scmVzcG9uZGVyOnQscmVxdWVzdDp7bWV0aG9kOiJjb25uZWN0aW9uL3VwZGF0ZSIsaWQ6bixjb25uZWN0aW9uOnIsInZpZXdlci1zdGF0ZSI6ZX19KX0sZW5kOmZ1bmN0aW9uKGUpe28ubmV4dElkKys7dmFyIHQ9by5uZXh0SWQsbj1zO28uX19leGVjdXRlX3JyKHtpZDp0LHJlc3BvbmRlcjplLHJlcXVlc3Q6e21ldGhvZDoiY29ubmVjdGlvbi9lbmQiLGlkOnQsY29ubmVjdGlvbjpufX0pfX0pfSxlLnByb3RvdHlwZS5Db25maWd1cmVNYWtlT3JHZXRBc3NldEtleT1mdW5jdGlvbihlKXt0aGlzLm5leHRJZCsrO3ZhciB0PXRoaXMubmV4dElkO3JldHVybiB0aGlzLl9fZXhlY3V0ZV9ycih7aWQ6dCxyZXNwb25kZXI6ZSxyZXF1ZXN0OnttZXRob2Q6ImNvbmZpZ3VyZS9tYWtlLW9yLWdldC1hc3NldC1rZXkiLGlkOnR9fSl9LGUucHJvdG90eXBlLkF0dGFjaG1lbnRTdGFydD1mdW5jdGlvbihlLHQsbixyLGksbyl7dmFyIHM9dGhpcztzLm5leHRJZCsrO3ZhciBjPXMubmV4dElkO3JldHVybiBzLl9fZXhlY3V0ZV9zdHJlYW0oe2lkOmMscmVzcG9uZGVyOm8scmVxdWVzdDp7bWV0aG9kOiJhdHRhY2htZW50L3N0YXJ0IixpZDpjLGlkZW50aXR5OmUsc3BhY2U6dCxrZXk6bixmaWxlbmFtZTpyLCJjb250ZW50LXR5cGUiOml9LGFwcGVuZDpmdW5jdGlvbihlLHQsbil7cy5uZXh0SWQrKzt2YXIgcj1zLm5leHRJZCxpPWM7cy5fX2V4ZWN1dGVfcnIoe2lkOnIscmVzcG9uZGVyOm4scmVxdWVzdDp7bWV0aG9kOiJhdHRhY2htZW50L2FwcGVuZCIsaWQ6cix1cGxvYWQ6aSwiY2h1bmstbWQ1IjplLCJiYXNlNjQtYnl0ZXMiOnR9fSl9LGZpbmlzaDpmdW5jdGlvbihlKXtzLm5leHRJZCsrO3ZhciB0PXMubmV4dElkLG49YztzLl9fZXhlY3V0ZV9ycih7aWQ6dCxyZXNwb25kZXI6ZSxyZXF1ZXN0OnttZXRob2Q6ImF0dGFjaG1lbnQvZmluaXNoIixpZDp0LHVwbG9hZDpufX0pfX0pfSxlfSgpfSxmdW5jdGlvbihlLHQpe3ZhciBuO249ZnVuY3Rpb24oKXtyZXR1cm4gdGhpc30oKTt0cnl7bj1ufHxuZXcgRnVuY3Rpb24oInJldHVybiB0aGlzIikoKX1jYXRjaChlKXsib2JqZWN0Ij09dHlwZW9mIHdpbmRvdyYmKG49d2luZG93KX1lLmV4cG9ydHM9bn1dKX0pKTs=");
}