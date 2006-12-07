// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.commons.ui.ws;

import org.eclipse.core.internal.runtime.InternalPlatform;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.BundleContext;

/**
 * 
 * DOC root class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
@SuppressWarnings("restriction")
public class WindowSystem {

    private static String ws;

    static {
        BundleContext bundleContext = InternalPlatform.getDefault().getBundleContext();
        if (bundleContext != null) {
            ws = Platform.getWS();
        }

    }

    public static boolean isGTK() {
        return Platform.WS_GTK.equals(ws);
    }

    /**
     * .
     * 
     * @return true if WIN32 or eclipse bundle is null
     */
    public static boolean isWIN32() {
        return Platform.WS_WIN32.equals(ws) || ws == null;
    }

}
