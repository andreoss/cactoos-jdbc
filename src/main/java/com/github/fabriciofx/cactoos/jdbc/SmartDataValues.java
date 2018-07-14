/*
 * The MIT License (MIT)
 *
 * Copyright (C) 2018 Fabrício Barros Cabral
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.fabriciofx.cactoos.jdbc;

import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Smart Data Values.
 *
 * <p>There is no thread-safety guarantee.
 *
 * @since 0.1
 */
public final class SmartDataValues implements DataValues {
    /**
     * Values.
     */
    private final List<DataValue<?>> values;

    /**
     * Ctor.
     * @param prms List of DataValue
     */
    public SmartDataValues(final DataValue<?>... prms) {
        this.values = Arrays.asList(prms);
    }

    @Override
    public DataValues with(final DataValue<?> value) {
        this.values.add(value);
        return this;
    }

    @Override
    public PreparedStatement prepare(final PreparedStatement stmt)
        throws Exception {
        int index = 1;
        for (final DataValue<?> value : this.values) {
            value.prepare(stmt, index);
            ++index;
        }
        return stmt;
    }

    @Override
    public Iterator<DataValue<?>> iterator() {
        return this.values.iterator();
    }
}
