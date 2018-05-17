package com.example.latte_core.delegates.main;

public class BottomItem {
    private CharSequence icon;
    private CharSequence iconName;

    public BottomItem(CharSequence icon, CharSequence iconName) {
        this.icon = icon;
        this.iconName = iconName;
    }

    public CharSequence getIcon() {
        return icon;
    }

    public CharSequence getIconName() {
        return iconName;
    }
}
