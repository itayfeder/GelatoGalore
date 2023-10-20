package com.itayfeder.gelato_galore.utils;

import net.minecraft.util.StringRepresentable;

import java.util.Arrays;
import java.util.Comparator;

public enum ScoopPattern implements StringRepresentable {
    CLEAR(0, "clear"),
    SPOTS(1, "spots");

    private static final ScoopPattern[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(ScoopPattern::getId)).toArray((p_41067_) -> {
        return new ScoopPattern[p_41067_];
    });

    private final int id;
    private final String name;

    private ScoopPattern(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public int getId() {
        return id;
    }

    public static ScoopPattern byId(int p_41054_) {
        if (p_41054_ < 0 || p_41054_ >= BY_ID.length) {
            p_41054_ = 0;
        }

        return BY_ID[p_41054_];
    }
}
