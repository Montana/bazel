// Copyright 2016 The Bazel Authors. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.testing.junit.runner.junit4;

import com.google.testing.junit.runner.util.Factory;
import java.io.PrintStream;
import java.util.function.Supplier;
import org.junit.internal.TextListener;

/**
 * A factory that supplies {@link TextListener}.
 */
public final class ProvideTextListenerFactory implements Factory<TextListener> {
  private final Supplier<PrintStream> testRunnerOutSupplier;

  public ProvideTextListenerFactory(
      Supplier<PrintStream> testRunnerOutSupplier) {
    assert testRunnerOutSupplier != null;
    this.testRunnerOutSupplier = testRunnerOutSupplier;
  }

  @Override
  public TextListener get() {
    TextListener textListener =
        JUnit4RunnerBaseModule.provideTextListener(testRunnerOutSupplier.get());
    assert textListener != null;
    return textListener;
  }

  public static Factory<TextListener> create(Supplier<PrintStream> testRunnerOutSupplier) {
    return new ProvideTextListenerFactory(testRunnerOutSupplier);
  }
}
