/*
 * Copyright (C) 2015 PÂRIS Quentin
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.playonlinux.framework;

import java.io.File;
import java.io.IOException;

import com.playonlinux.ui.api.ProgressControl;

public class Checksum {
    ProgressControl progressControl;

    public Checksum progress(ProgressControl progressControl) {
        this.progressControl = progressControl;
        return this;
    }

    public String md5(String file) throws IOException {
        com.playonlinux.core.utils.ChecksumCalculator checksumCalculatorCalculator = new com.playonlinux.core.utils.ChecksumCalculator();
        if(progressControl != null) {
            checksumCalculatorCalculator.setOnChange(progressControl);
        }
        return checksumCalculatorCalculator.calculate(new File(file), "md5");
    }

    public String sha1(String file) throws IOException {
        com.playonlinux.core.utils.ChecksumCalculator checksumCalculatorCalculator = new com.playonlinux.core.utils.ChecksumCalculator();
        if(progressControl != null) {
            checksumCalculatorCalculator.setOnChange(progressControl);
        }
        return checksumCalculatorCalculator.calculate(new File(file), "sha1");
    }
}
