/**
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
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.fabriciofx.cactoos.jdbc.agenda;

import org.cactoos.Text;

/**
 * @author Fabricio Cabral (fabriciofx@gmail.com)
 * @version Id
 * @since
 */
public interface Contact extends Text {
    Contact NOBODY = new Contact() {
        @Override
        public String name() throws Exception {
            throw new UnsupportedOperationException();
        }
        @Override
        public Phones phones() throws Exception {
            throw new UnsupportedOperationException();
        }
        @Override
        public void delete() throws Exception {
            throw new UnsupportedOperationException();
        }
        @Override
        public void rename(final String name) throws Exception {
            throw new UnsupportedOperationException();
        }
        @Override
        public String asString() throws Exception {
            throw new UnsupportedOperationException();
        }
    };

    String name() throws Exception;

    Phones phones() throws Exception;

    void delete() throws Exception;

    void rename(String name) throws Exception;
}