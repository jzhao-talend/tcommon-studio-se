// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.swtbot.items;

import java.io.IOException;
import java.net.URISyntaxException;

import junit.framework.Assert;

import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.talend.swtbot.DndUtil;
import org.talend.swtbot.Utilities;

/**
 * DOC fzhong class global comment. Detailled comment
 */
public class TalendEdiItem extends TalendMetadataItem {

    private String standard;

    private String release;

    private String[] schemas;

    private String filePath;

    public TalendEdiItem() {
        initialise(Utilities.TalendItemType.EDI);
    }

    public TalendEdiItem(String itemName, String standard, String release, String[] schema) {
        initialise(Utilities.TalendItemType.EDI);
        this.itemName = itemName;
        this.standard = standard;
        this.release = release;
        this.schemas = schema;
    }

    public String getStandard() {
        return this.standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getRelease() {
        return this.release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String[] getSchemas() {
        return this.schemas;
    }

    public void setSchemas(String[] schemas) {
        this.schemas = schemas;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getAbsoluteFilePath() throws IOException, URISyntaxException {
        return Utilities.getFileFromCurrentPluginSampleFolder(filePath).getAbsolutePath();
    }

    @Override
    public void create() {
        SWTGefBot gefBot = new SWTGefBot();
        SWTBotTreeItem treeNode = Utilities.getTalendItemNode(getItemType());
        treeNode.contextMenu("Create EDI").click();
        SWTBotShell shell = gefBot.shell("Create new EDI schema").activate();
        gefBot.textWithLabel("Name").setText(getItemName());
        boolean isNextButtonEnable = gefBot.button("Next >").isEnabled();
        if (!isNextButtonEnable) {
            shell.close();
            Assert.assertTrue("edi item is not created, maybe the item name already exist", isNextButtonEnable);
        }
        gefBot.button("Next >").click();

        try {
            gefBot.tree().expandNode(getStandard()).getNode(getRelease()).click();
            gefBot.button("Next >").click();

            String[] schemas = getSchemas();
            for (int i = 0; i < schemas.length; i++) {
                SWTBotTreeItem sourceItem = gefBot.tree(0).expandNode("BGM(Beginning_of_message)", "DOCUMENT_MESSAGE_NAME")
                        .getNode(schemas[i]).click();
                SWTBotTable targetItem = gefBot.tableInGroup("Schema");
                DndUtil dndUtil = new DndUtil(shell.display);
                dndUtil.dragAndDrop(sourceItem, targetItem);
            }
            gefBot.button("Next >").click();
            gefBot.button("Finish").click();
        } catch (WidgetNotFoundException wnfe) {
            shell.close();
            Assert.fail(wnfe.getCause().getMessage());
        } catch (Exception e) {
            shell.close();
            Assert.fail(e.getMessage());
        }

        SWTBotTreeItem newEDIItem = null;
        try {
            newEDIItem = treeNode.expand().select(getItemName() + " 0.1");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Assert.assertNotNull("validation EDI item is not created", newEDIItem);
        }

        setItem(treeNode.getNode(getItemName() + " 0.1"));
    }
}
