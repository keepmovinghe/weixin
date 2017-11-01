package com.keep.menu;

/**
 * 复合类型按钮
 *
 * @author chenxh
 * @date 2017/6/27
 */
public class ComplexButton extends Button {

    private Button[] sub_button;

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
