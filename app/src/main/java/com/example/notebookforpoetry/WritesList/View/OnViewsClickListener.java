package com.example.notebookforpoetry.WritesList.View;

import android.view.View;


public interface OnViewsClickListener<T>  {
    /**
     * Сообщает о клике по элементу списка
     *
     * @param view     вьюха, которая была нажата
     * @param element  элемент
     * @param position позиция в адаптере
     */
    void onItemClick(View view, T element, int position);
}
