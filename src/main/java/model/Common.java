package model;

import saveload.SaveDate;

abstract public class Common {

    public Common() {}

    public String getValueForCombobox() {
        return null;
    }

    public void postAdd(SaveDate sd) {}
    public void postEdit(SaveDate sd) {}
    public void postDelete(SaveDate sd) {}

}
