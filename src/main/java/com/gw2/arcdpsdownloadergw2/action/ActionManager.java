package com.gw2.arcdpsdownloadergw2.action;

public class ActionManager {

    /**
     * Executes the actions if they could be found.
     * 
     * @param actions Names of actions to perform
     * @return <code>false</code> if one of the actions could not be executed,
     *         <code>true</code> otherwise.
     */
    public boolean execute(String... actions) {
        for (String a : actions) {
            Class<?> clazz;
            Action action;
            try {
                clazz = Class.forName(getClass().getPackageName() + ".impl." + a);
            } catch (ClassNotFoundException e) {
                clazz = null;
            }
            if (clazz == null || !Action.class.isAssignableFrom(clazz)) {
                System.out.println("Action not available");
                continue;
            }
            try {
                action = (Action) clazz.getConstructor().newInstance();
            } catch (Exception e) {
                action = null;
            }
            if (action != null) {
                try {
                    action.execute();
                } catch (Throwable e) {
                    System.out.println("Error while executing action " + a + ": " + e.getMessage());
                    return false;
                }
            } else {
                System.out.println("Action not available");
            }
        }
        return true;
    }

}
