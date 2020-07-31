package com.android.rolebaseaccesscontrollibrary;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RBACLibUnitTest {
/*    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }*/

    @Test
    public void showAllWebPages() {
        RBACManager.getInstance().printAllWebPagesList();
    }

    @Test
    public void testRBACAddUsers() {

        RBACManager.getInstance().addUser(
                1, "Zain Abbas"
        );

        RBACManager.getInstance().addUser(
                2, "Aun Abbas"
        );

        RBACManager.getInstance().addUser(
                3, "Ibrahim Abbas"
        );

    }

    @Test
    public void testRBACRemoveUser() {

        RBACManager.getInstance().removeUser(3);

    }

    @Test
    public void testRBACAssignPermissionToWebPage() {

        RBACManager.getInstance().assignedWebPageToUser(
                1, 1
        );

        RBACManager.getInstance().assignedWebPageToUser(
                1, 2
        );

        RBACManager.getInstance().assignedWebPageToUser(
                2, 1
        );

        RBACManager.getInstance().assignedWebPageToUser(
                3, 3
        );

        RBACManager.getInstance().printAllPermissions();
    }

    @Test
    public void testRBACQueryingForPermissions() {

        RBACManager.getInstance().canUserAccessWebsite(1, 1);

        RBACManager.getInstance().canUserAccessWebsite(1, 2);

        RBACManager.getInstance().canUserAccessWebsite(1, 3);

        RBACManager.getInstance().canUserAccessWebsite(2, 1);

        RBACManager.getInstance().canUserAccessWebsite(2, 2);
    }
}