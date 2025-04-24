package com.sbs.java.board.global.interceptor;

import com.sbs.java.board.global.base.Rq;

public interface Interceptor {
  boolean run(Rq rq);
}
