package com.android.rolebaseaccesscontrollibrary;

import com.android.rolebaseaccesscontrollibrary.models.Role;
import com.android.rolebaseaccesscontrollibrary.models.User;
import com.android.rolebaseaccesscontrollibrary.models.WebPage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Asad Ullah 
 * <p>
 * This class will handle all the business logic.
 */
public class RBACManager {

    private final String TAG = this.getClass().getName();
    private static RBACManager INSTANCE = null;

    public static synchronized RBACManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RBACManager();
        }
        return INSTANCE;
    }

    private HashSet<WebPage> webPageSet;
    private HashSet<User> userSet;

    //PermissionList that contains webPage Id as Key and list of UserIds as values.
    private HashMap<Integer, Set<Integer>> permissionsList;

    public RBACManager() {
        webPageSet = new HashSet<>();
        userSet = new HashSet<>();
        permissionsList = new HashMap<>();

        //Setting the dummy data for webPages.
        setDefaultWebPageData();
    }

    private void setDefaultWebPageData() {
        webPageSet.add(new WebPage(1, "https://google.com"));
        webPageSet.add(new WebPage(2, "https://yahoo.com"));
        webPageSet.add(new WebPage(3, "https://github.com"));
    }


    /**
     * Adding the User with default Role
     *
     * @param id
     * @param name
     */
    public void addUser(int id, String name) {
        userSet.add(new User(id, name, new Role(1, "Normal")));

        //Log.d(TAG, "User{Id: " + id + ", Name: " + name + "} ADDED Success.");
        System.out.println("User{Id: " + id + ", Name: " + name + "} ADDED Success.");

    }

    /**
     * Remove User.
     *
     * @param id
     */
    public void removeUser(int id) {

        User userToDelete = null;

        for (User user : userSet) {
            if (user.getId() == id) {
                userToDelete = user;
                break;
            }
        }

        if (userToDelete != null) {
            userSet.remove(userToDelete);

            System.out.println("User{Id: " + userToDelete.getId() + ", Name: " + userToDelete.getName() + "} DELETED Success.");
        }
    }

    public void assignedWebPageToUser(int userId, int webPageId) {
        Set<Integer> userAccessIds = new HashSet<>();

        if (permissionsList.containsKey(webPageId) && permissionsList.get(webPageId) != null)
            userAccessIds = permissionsList.get(webPageId);

        userAccessIds.add(userId);

        permissionsList.put(webPageId, userAccessIds);

    }

    public boolean canUserAccessWebsite(int userId, int webPageId) {

        boolean hasAccess = false;

        if (permissionsList != null && permissionsList.containsKey(webPageId)) {

            if (permissionsList.get(webPageId) != null && permissionsList.get(webPageId).contains(userId)) {
                hasAccess = true;
            }
        }

        if (getUserById(userId) == null) {
            System.out.println("User not Exist");
        } else {

            User user = getUserById(userId);

            String access = " Access Denied On " + getWebPageById(webPageId).getUrl();

            if (hasAccess)
                access = " Access Granted On " + getWebPageById(webPageId).getUrl();

            System.out.println(user.getName() + access);

        }

        return hasAccess;

    }

    //S ====== Helper Functions ======

    public User getUserById(int userId) {
        User user = null;

        for (Iterator<User> it = userSet.iterator(); it.hasNext(); ) {
            User f = it.next();
            if (f.getId() == userId) {
                user = f;
                break;
            }
        }

        return user;
    }

    public WebPage getWebPageById(int webPageId) {
        WebPage webPage = null;

        for (Iterator<WebPage> it = webPageSet.iterator(); it.hasNext(); ) {
            WebPage f = it.next();
            if (f.getId() == webPageId) {
                webPage = f;
                break;
            }
        }

        return webPage;
    }

    public void printAllWebPagesList() {
        System.out.println("======= All System WebPages ======");

        for (WebPage webPage : webPageSet) {
            System.out.println("Id: " + webPage.getId() + " URL: " + webPage.getUrl());
        }


    }

    public void printAllPermissions() {
        System.out.println("======= All Permissions To users for WebPages [WebPage = {List Of Users}] ======");

        for (Map.Entry<Integer, Set<Integer>> entry : permissionsList.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

    }

    public void runDemoApp() {

        showAllWebPages();

        addUsers();

        removeUser(3);

        assignPermissionToWebPage();

        queryingForPermissions();
    }

    public void showAllWebPages() {
        RBACManager.getInstance().printAllWebPagesList();
    }

    public void addUsers() {

        System.out.println("\n======= Adding Dummy Users...");

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

    public void assignPermissionToWebPage() {

        System.out.println("====== Assigning Permissions...\n");

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

    public void queryingForPermissions() {

        System.out.println("====== Querying Permissions...\n");

        RBACManager.getInstance().canUserAccessWebsite(1, 1);

        RBACManager.getInstance().canUserAccessWebsite(1, 2);

        RBACManager.getInstance().canUserAccessWebsite(1, 3);

        RBACManager.getInstance().canUserAccessWebsite(2, 1);

        RBACManager.getInstance().canUserAccessWebsite(2, 2);
    }

    //E ====== Helper Functions ======
}
