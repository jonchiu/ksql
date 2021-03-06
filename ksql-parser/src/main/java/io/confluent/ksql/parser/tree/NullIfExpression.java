/**
 * Copyright 2017 Confluent Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/

package io.confluent.ksql.parser.tree;

import java.util.Objects;
import java.util.Optional;

/**
 * NULLIF(V1,V2): CASE WHEN V1=V2 THEN NULL ELSE V1 END
 */
public class NullIfExpression
    extends Expression {

  private final Expression first;
  private final Expression second;

  public NullIfExpression(final Expression first, final Expression second) {
    this(Optional.empty(), first, second);
  }

  public NullIfExpression(
      final NodeLocation location,
      final Expression first,
      final Expression second) {
    this(Optional.of(location), first, second);
  }

  private NullIfExpression(
      final Optional<NodeLocation> location, final Expression first, final Expression second) {
    super(location);
    this.first = first;
    this.second = second;
  }

  public Expression getFirst() {
    return first;
  }

  public Expression getSecond() {
    return second;
  }

  @Override
  public <R, C> R accept(final AstVisitor<R, C> visitor, final C context) {
    return visitor.visitNullIfExpression(this, context);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final NullIfExpression that = (NullIfExpression) o;
    return Objects.equals(first, that.first)
           && Objects.equals(second, that.second);
  }

  @Override
  public int hashCode() {
    return Objects.hash(first, second);
  }
}
