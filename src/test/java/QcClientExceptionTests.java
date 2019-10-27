/*
 * QC REST API client
 *
 * Copyright (C) 2019  matthias.dirickx@outlook.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
import be.mdi.testing.qc.exception.QcTypeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QcClientExceptionTests {

    @Test
    public void theExceptiionWithAMessageIsThrown() {
        try {
            throw new QcTypeException("The message");
        } catch(QcTypeException e) {
            Assertions.assertEquals("The message", e.getMessage());
        }
    }

    @Test
    public void theExceptionWithMessageAndThrowableIsThrown() {
        try {
            throw new QcTypeException("The message", new Exception());
        } catch(QcTypeException e) {
            Assertions.assertEquals(Exception.class, e.getCause().getClass());
            Assertions.assertEquals("The message", e.getMessage());
        }
    }

    @Test
    public void theExceptionWithThrowableIsThrown() {
        try {
            throw new QcTypeException(new Exception());
        } catch(QcTypeException e) {
            Assertions.assertEquals(Exception.class, e.getCause().getClass());
        }
    }
}
