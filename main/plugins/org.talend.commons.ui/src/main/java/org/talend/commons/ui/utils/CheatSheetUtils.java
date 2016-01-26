// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.commons.ui.utils;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.cheatsheets.CheatSheetPlugin;
import org.eclipse.ui.internal.cheatsheets.ICheatSheetResource;
import org.eclipse.ui.internal.cheatsheets.Messages;
import org.eclipse.ui.internal.cheatsheets.views.CheatSheetView;
import org.eclipse.ui.internal.util.PrefUtil;

/**
 * this class is used for cheatsheet view display.
 * 
 */
@SuppressWarnings("restriction")
public class CheatSheetUtils {

    private static CheatSheetUtils instance;

    private IPartListener2 partListener2 = null;

    /**
     * this flag means the first time when studio start. only when this value is true, when close the welcome page, we
     * will display and maximum display cheatsheet view, else do nothing.
     */
    private boolean isFirstTime = !PrefUtil.getAPIPreferenceStore().getBoolean(this.getClass().getSimpleName());

    private boolean maxCheatSheetHasSHow = false;

    /**
     * Sets the isFirstTime.
     * 
     * @param isFirstTime the isFirstTime to set
     */
    public void setFirstTime(boolean isFirstTime) {
        this.isFirstTime = isFirstTime;
    }

    /**
     * Getter for isFirstTime.
     * 
     * @return the isFirstTime
     */
    public boolean isFirstTime() {
        return this.isFirstTime;
    }

    public static final String DQ_PERSPECTIVE_ID = "org.talend.dataprofiler.DataProfilingPerspective";//$NON-NLS-1$

    public static final String DQ_CHEATSHEET_START_ID = "org.talend.datacleansing.core.ui.dqcheatsheet";//$NON-NLS-1$ 

    private static Logger log = Logger.getLogger(CheatSheetUtils.class);

    public static CheatSheetUtils getInstance() {
        if (instance == null) {
            instance = new CheatSheetUtils();
        }
        return instance;
    }

    /**
     * maximum display CheatSheetView.
     * 
     * @param view
     */
    public void maxDisplayCheatSheetView(CheatSheetView view) {
        // ADD msjian TDQ-7407 2013-8-23: Only display the Cheat Sheet view on new startup of the studio
        IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        activePage.setEditorAreaVisible(true);
        // activePage.resetPerspective();
        IViewPart cheatSheetView = null;
        for (IViewReference ref : activePage.getViewReferences()) {
            cheatSheetView = ref.getView(false);
            if (view.equals(cheatSheetView)) {
                activePage.setPartState(ref, IWorkbenchPage.STATE_MAXIMIZED);
                activePage.bringToTop(cheatSheetView);
            }
        }
        setMaxCheatSheetHasSHow(true);

        PrefUtil.getAPIPreferenceStore().setValue(this.getClass().getSimpleName(), true);
        setFirstTime(!PrefUtil.getAPIPreferenceStore().getBoolean(this.getClass().getSimpleName()));
        partListener2 = new PartListener2Adapter() {

            @Override
            public void partHidden(IWorkbenchPartReference partRef) {
                restoreOtherViewAndEditor(partRef.getPart(false));

            }
        };
        activePage.addPartListener(partListener2);
        // TDQ-7407~
    }

    /**
     * DOC talend Comment method "restoreOtherViewAndEditor".
     * 
     * @param part
     */
    protected void restoreOtherViewAndEditor(IWorkbenchPart part) {
        if (part instanceof org.eclipse.ui.internal.cheatsheets.views.CheatSheetView) {
            IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
            if (activePage == null) {
                return;
            }
            for (IViewReference ref : activePage.getViewReferences()) {
                if (part != ref.getView(false)) {
                    activePage.setPartState(ref, IWorkbenchPage.STATE_RESTORED);
                }
            }
            for (IEditorReference ref : activePage.getEditorReferences()) {
                activePage.setPartState(ref, IWorkbenchPage.STATE_RESTORED);
            }
            CheatSheetUtils.getInstance().setMaxCheatSheetHasSHow(false);
            if (partListener2 != null) {
                activePage.removePartListener(partListener2);
            }
        }
    }

    /**
     * find And maximum Display CheatSheet.
     */
    public void findAndmaxDisplayCheatSheet(String cheatSheetID) {
        CheatSheetView findCheetSheet = findCheetSheet(cheatSheetID);
        if (findCheetSheet != null) {
            maxDisplayCheatSheetView(findCheetSheet);
        }
    }

    /**
     * get the cheet sheet view. If can not find the veiw then create new one
     * 
     * @return CheatSheetView
     */
    public CheatSheetView findCheetSheet(String cheatSheetID) {

        return findCheetSheet(cheatSheetID, true);

    }

    /**
     * get the cheet sheet view.
     * 
     * @return CheatSheetView
     */
    public CheatSheetView findCheetSheet(String cheatSheetID, boolean froceOpen) {
        IWorkbench workbench = PlatformUI.getWorkbench();
        IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
        IWorkbenchPage page = window.getActivePage();
        CheatSheetView view = (CheatSheetView) page.findView(ICheatSheetResource.CHEAT_SHEET_VIEW_ID);

        IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        if ((view == null || view.getCheatSheetID() == null) && activePage != null
                && activePage.getPerspective().getId().equals(DQ_PERSPECTIVE_ID)) {
            try {
                if (froceOpen == true) {
                    // page.isPartVisible(view); when the result is true then this view should show again
                    view = (CheatSheetView) page.showView(ICheatSheetResource.CHEAT_SHEET_VIEW_ID);
                }
                if (view != null) {
                    view.setInput(cheatSheetID);
                    page.activate(view);
                }
            } catch (PartInitException pie) {
                String message = Messages.LAUNCH_SHEET_ERROR;
                IStatus status = new Status(IStatus.ERROR, ICheatSheetResource.CHEAT_SHEET_PLUGIN_ID, IStatus.OK, message, pie);
                CheatSheetPlugin.getPlugin().getLog().log(status);
                org.eclipse.jface.dialogs.ErrorDialog.openError(window.getShell(), Messages.CHEAT_SHEET_ERROR_OPENING, null,
                        pie.getStatus());
            }
        }
        return view;

    }

    /**
     * hide the cheet sheet view.
     * 
     * @param cheetSheet
     */
    public void hideCheetSheet(CheatSheetView cheetSheet) {
        try {
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().hideView(cheetSheet);
        } catch (Throwable t) {
            log.warn(t, t);
        }
    }

    /**
     * Getter for maxCheatSheetHasSHow.
     * 
     * @return the maxCheatSheetHasSHow
     * */
    public boolean isMaxCheatSheetHasSHow() {
        return this.maxCheatSheetHasSHow;
    }

    /**
     * Sets the maxCheatSheetHasSHow.
     * 
     * @param maxCheatSheetHasSHow the maxCheatSheetHasSHow to set
     */
    public void setMaxCheatSheetHasSHow(boolean maxCheatSheetHasSHow) {
        this.maxCheatSheetHasSHow = maxCheatSheetHasSHow;
    }
}
