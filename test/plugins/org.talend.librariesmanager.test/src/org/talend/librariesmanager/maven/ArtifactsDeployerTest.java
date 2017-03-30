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
package org.talend.librariesmanager.maven;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.maven.model.Model;
import org.eclipse.m2e.core.MavenPlugin;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.talend.commons.utils.resource.BundleFileUtil;
import org.talend.core.model.general.ModuleNeeded.ELibraryInstallStatus;
import org.talend.core.model.general.ModuleStatusProvider;
import org.talend.utils.io.FilesUtils;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class ArtifactsDeployerTest {

    File tmpFolder;

    File localJarFile, localPomFile, localZipFile;

    File repoJarFile, repoPomFile;

    @Before
    public void prepare() throws Exception {
        tmpFolder = org.talend.utils.files.FileUtils.createTmpFolder(this.getClass().getSimpleName(), ""); //$NON-NLS-1$  //$NON-NLS-2$

        final File testDataFile = BundleFileUtil.getBundleFile(this.getClass(), "resources/m2/mytest_jar.zip");
        Assert.assertNotNull(testDataFile);
        Assert.assertTrue(testDataFile.exists());
        FilesUtils.unzip(testDataFile.getAbsolutePath(), tmpFolder.getAbsolutePath());
        localZipFile = new File(tmpFolder, testDataFile.getName());
        FilesUtils.copyFile(testDataFile, localZipFile);

        localJarFile = new File(tmpFolder, "mytest-6.0.0.jar");
        localPomFile = new File(tmpFolder, "mytest-6.0.0.pom");
        Assert.assertTrue(localJarFile.exists());
        Assert.assertTrue(localPomFile.exists());

        final String localRepositoryPath = MavenPlugin.getMaven().getLocalRepositoryPath();
        Assert.assertNotNull(localRepositoryPath);
        File localRepoFolder = new File(localRepositoryPath);
        Assert.assertTrue(localRepoFolder.exists());

        repoJarFile = new File(localRepoFolder, "org/talend/libraries/mytest/6.0.0/mytest-6.0.0.jar");
        if (repoJarFile.exists()) {
            repoJarFile.delete();
        }
        repoPomFile = new File(localRepoFolder, "org/talend/libraries/mytest/6.0.0/mytest-6.0.0.pom");
        if (repoPomFile.exists()) {
            repoPomFile.delete();
        }

        Assert.assertFalse("Can't clean the existed jar from local m2", repoJarFile.exists());
        Assert.assertFalse("Can't clean the existed pom from local m2", repoPomFile.exists());
    }

    @After
    public void clean() {
        if (tmpFolder != null) {
            FilesUtils.deleteFolder(tmpFolder, true);
        }
    }

    /**
     * Test for ArtifactsDeployer.deployToLocalMaven(Map<String, String> jarSourceAndMavenUri, boolean updateRemoteJar)
     */
    @Test
    public void test_deployToLocalMaven_emptyMap() throws Exception {
        Map<String, String> jarSourceAndMavenUri = new HashMap<String, String>();
        new ArtifactsDeployer().deployToLocalMaven(jarSourceAndMavenUri, false);

        Assert.assertFalse(repoJarFile.exists());
        Assert.assertFalse(repoPomFile.exists());
    }

    @Test
    public void test_deployToLocalMaven_map() throws Exception {
        Map<String, String> jarSourceAndMavenUri = new HashMap<String, String>();
        jarSourceAndMavenUri.put("mvn:org.talend.libraries/mytest/6.0.0/jar", localJarFile.getAbsolutePath());
        new ArtifactsDeployer().deployToLocalMaven(jarSourceAndMavenUri, false);

        Assert.assertTrue(repoJarFile.exists());
        Assert.assertTrue(repoPomFile.exists());
    }

    /**
     * 
     * Test for ArtifactsDeployer.deployToLocalMaven(String path, String mavenUri, boolean toRemoteNexus)
     */
    @Test
    public void test_deployToLocalMaven_generatePom() throws Exception {
        new ArtifactsDeployer().deployToLocalMaven(localJarFile.getAbsolutePath(), "mvn:org.talend.libraries/mytest/6.0.0/jar",
                false);

        Assert.assertTrue(repoJarFile.exists());
        Assert.assertTrue(repoPomFile.exists());

        Model model = MavenPlugin.getMaven().readModel(repoPomFile);
        Assert.assertNotNull(model);
        Assert.assertNull("It's generated pom. so don't set description", model.getDescription());
    }

    /**
     * Test for ArtifactsDeployer.deployToLocalMaven(String mavenUri, String libPath, String pomPath, boolean
     * toRemoteNexus)
     */
    @Test
    public void test_deployToLocalMaven_withPom_null() throws Exception {
        new ArtifactsDeployer().deployToLocalMaven(null, null, null, false);
        Assert.assertFalse(repoJarFile.exists());
        Assert.assertFalse(repoPomFile.exists());

        new ArtifactsDeployer().deployToLocalMaven("mvn:org.talend.libraries/mytest/6.0.0/jar", null, null, false);
        Assert.assertFalse(repoJarFile.exists());
        Assert.assertFalse(repoPomFile.exists());

        new ArtifactsDeployer().deployToLocalMaven("mvn:org.talend.libraries/mytest/6.0.0/jar", "", null, false);
        Assert.assertFalse(repoJarFile.exists());
        Assert.assertFalse(repoPomFile.exists());

        new ArtifactsDeployer().deployToLocalMaven("mvn:org.talend.libraries/mytest/6.0.0/jar", "abc.jar", null, false);
        Assert.assertFalse(repoJarFile.exists());
        Assert.assertFalse(repoPomFile.exists());
    }

    @Test
    public void test_deployToLocalMaven_withPom_jar() throws Exception {
        final String mavenUri = "mvn:org.talend.libraries/mytest/6.0.0/jar";
        new ArtifactsDeployer().deployToLocalMaven(mavenUri, localJarFile.getAbsolutePath(), localPomFile.getAbsolutePath(),
                false);

        Assert.assertEquals(ELibraryInstallStatus.DEPLOYED, ModuleStatusProvider.getDeployStatusMap().get(mavenUri));
        Assert.assertEquals(ELibraryInstallStatus.INSTALLED, ModuleStatusProvider.getStatusMap().get(mavenUri));

        Assert.assertTrue(repoJarFile.exists());
        Assert.assertTrue(repoPomFile.exists());

        Model model = MavenPlugin.getMaven().readModel(repoPomFile);
        Assert.assertNotNull(model);
        Assert.assertEquals("Should have same description of pom with original one", "It's a test jar", model.getDescription());
    }

    @Test
    public void test_deployToLocalMaven_withPom_jar_withoutPackaging() throws Exception {
        final String mavenUri = "mvn:org.talend.libraries/mytest/6.0.0";
        new ArtifactsDeployer().deployToLocalMaven(mavenUri, localJarFile.getAbsolutePath(), localPomFile.getAbsolutePath(),
                false);

        Assert.assertEquals(ELibraryInstallStatus.DEPLOYED, ModuleStatusProvider.getDeployStatusMap().get(mavenUri));
        Assert.assertEquals(ELibraryInstallStatus.INSTALLED, ModuleStatusProvider.getStatusMap().get(mavenUri));

        Assert.assertTrue(repoJarFile.exists());
        Assert.assertTrue(repoPomFile.exists());

        Model model = MavenPlugin.getMaven().readModel(repoPomFile);
        Assert.assertNotNull(model);
        Assert.assertEquals("Should have same description of pom with original one", "It's a test jar", model.getDescription());
    }

    @Test
    public void test_deployToLocalMaven_zip() throws Exception {
        final String mavenUri = "mvn:org.talend.libraries/mytest_jar/6.4.1/zip";
        new ArtifactsDeployer().deployToLocalMaven(mavenUri, localZipFile.getAbsolutePath(), null, false);

        Assert.assertEquals(ELibraryInstallStatus.DEPLOYED, ModuleStatusProvider.getDeployStatusMap().get(mavenUri));
        Assert.assertEquals(ELibraryInstallStatus.INSTALLED, ModuleStatusProvider.getStatusMap().get(mavenUri));

        final String localRepositoryPath = MavenPlugin.getMaven().getLocalRepositoryPath();
        Assert.assertNotNull(localRepositoryPath);
        File localRepoFolder = new File(localRepositoryPath);
        Assert.assertTrue(localRepoFolder.exists());

        File repoZipFile = new File(localRepoFolder, "org/talend/libraries/mytest_jar/6.4.1/mytest_jar-6.4.1.zip");
        Assert.assertTrue(repoZipFile.exists());
        File repoPomFile = new File(localRepoFolder, "org/talend/libraries/mytest_jar/6.4.1/mytest_jar-6.4.1.pom");
        Assert.assertTrue(repoPomFile.exists());
    }
}
