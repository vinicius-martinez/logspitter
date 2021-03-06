/*
 * The MIT License
 *
 * Copyright (c) 2014 Marcelo Guimaraes <ataxexe@gmail.com>
 *
 * Permission  is hereby granted, free of charge, to any person obtaining
 * a  copy  of  this  software  and  associated  documentation files (the
 * "Software"),  to  deal  in the Software without restriction, including
 * without  limitation  the  rights to use, copy, modify, merge, publish,
 * distribute,  sublicense,  and/or  sell  copies of the Software, and to
 * permit  persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The  above  copyright  notice  and  this  permission  notice  shall be
 * included  in  all  copies  or  substantial  portions  of the Software.
 *
 * THE  SOFTWARE  IS  PROVIDED  "AS  IS",  WITHOUT  WARRANTY OF ANY KIND,
 * EXPRESS  OR  IMPLIED,  INCLUDING  BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN  NO  EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM,  DAMAGES  OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT  OR  OTHERWISE,  ARISING  FROM,  OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE   OR   THE   USE   OR   OTHER   DEALINGS  IN  THE  SOFTWARE.
 */

package tools.devnull.logspitter.impl;

import tools.devnull.logspitter.ExceptionCreator;
import tools.devnull.logspitter.LogForwarder;
import tools.devnull.logspitter.LogSpitter;
import tools.devnull.logspitter.SpitterExceptionConfig;
import tools.devnull.logspitter.SpitterMessageConfig;

public class SpitterMessageConfigImpl implements SpitterMessageConfig {

  private final ExceptionCreator exceptionCreator;
  private final LogForwarder logForwarder;
  private final String level;
  private final String message;

  public SpitterMessageConfigImpl(LogForwarder logForwarder, ExceptionCreator exceptionCreator,
                                  String level, String message) {
    this.logForwarder = logForwarder;
    this.exceptionCreator = exceptionCreator;
    this.level = level;
    this.message = message;
  }

  @Override
  public SpitterExceptionConfig ofCategory(String category) {
    return new SpitterExceptionConfigImpl(logForwarder, exceptionCreator, level, message, category);
  }

  @Override
  public void thrownBy(String exceptionClass) {
    logForwarder.forward(level, LogSpitter.class.getPackage().getName(), message,
        exceptionCreator.create(exceptionClass, message));
  }

  @Override
  public void plain() {
    logForwarder.forward(level, LogSpitter.class.getPackage().getName(), message);
  }

}
