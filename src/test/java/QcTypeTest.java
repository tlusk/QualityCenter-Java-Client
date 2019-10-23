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

import be.mdi.testing.qc.model.QcType;
import be.mdi.testing.qc.model.entities.QcDefect;
import org.junit.jupiter.api.Test;

public class QcTypeTest {

    @Test
    public void theQcTypeObjectReturnValues() {
        QcType t = QcType.DEFECT;
        assert t.getDbType().equals("BUG");
        assert t.getSmallCapType().equals("defect");
        assert t.getRestUrlType().equals("defects");
        assert t.getParentIdentifier() == null;
        assert t.getRetType() == QcDefect.class;
    }
}
