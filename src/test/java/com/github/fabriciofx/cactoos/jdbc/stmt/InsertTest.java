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
package com.github.fabriciofx.cactoos.jdbc.stmt;

import com.github.fabriciofx.cactoos.jdbc.Databases;
import com.github.fabriciofx.cactoos.jdbc.Session;
import com.github.fabriciofx.cactoos.jdbc.query.KeyedQuery;
import com.github.fabriciofx.cactoos.jdbc.query.SimpleQuery;
import com.github.fabriciofx.cactoos.jdbc.result.ResultAsValue;
import com.github.fabriciofx.cactoos.jdbc.result.ResultAsValues;
import com.github.fabriciofx.cactoos.jdbc.value.TextValue;
import org.cactoos.text.JoinedText;
import org.hamcrest.MatcherAssert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.llorllale.cactoos.matchers.ScalarHasValue;

/**
 * Insert tests.
 *
 * <p>There is no thread-safety guarantee.
 *
 * @since 0.1
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle ClassDataAbstractionCouplingCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class InsertTest {
    private static Databases DATABASES = new Databases();

    @BeforeClass
    public static void setupClass() throws Exception {
        InsertTest.DATABASES.start();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        InsertTest.DATABASES.stop();
    }

    @Test
    public void insert() throws Exception {
        for (final Session session : InsertTest.DATABASES.sessions()) {
            new Update(
                session,
                new SimpleQuery(
                    new JoinedText(
                        " ",
                        "CREATE TABLE testdb.t01 (id INT AUTO_INCREMENT,",
                        "name VARCHAR(50), PRIMARY KEY (id))"
                    )
                )
            ).result();
            MatcherAssert.assertThat(
                "Can't insert into table",
                new ResultAsValue<>(
                    new Insert(
                        session,
                        new SimpleQuery(
                            "INSERT INTO testdb.t01 (name) VALUES (:name)",
                            new TextValue("name", "Yegor Bugayenko")
                        )
                    ),
                    Boolean.class
                ),
                new ScalarHasValue<>(false)
            );
        }
    }

    @Test
    public void insertWithKeys() throws Exception {
        for (final Session session : InsertTest.DATABASES.sessions()) {
            new Update(
                session,
                new SimpleQuery(
                    new JoinedText(
                        " ",
                        "CREATE TABLE testdb.t02 (id INT AUTO_INCREMENT,",
                        "name VARCHAR(50), PRIMARY KEY (id))"
                    )
                )
            ).result();
            MatcherAssert.assertThat(
                "Can't insert with an integer keys",
                () -> new ResultAsValues<>(
                    new InsertWithKeys(
                        session,
                        new KeyedQuery(
                            "INSERT INTO testdb.t02 (name) VALUES (:name)",
                            new TextValue("name", "Jeff Malony")
                        )
                    ),
                    Integer.class
                ).value().get(0),
                new ScalarHasValue<>(1)
            );
        }
    }
}
