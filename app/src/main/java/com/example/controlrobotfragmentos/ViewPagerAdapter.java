package com.example.controlrobotfragmentos;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import com.example.controlrobotfragmentos.fragmentos.ControlVertical;
import com.example.controlrobotfragmentos.fragmentos.controlHorizontal;

import com.example.controlrobotfragmentos.fragmentos.ControlVoz;
import com.example.controlrobotfragmentos.fragmentos.ControlVertical;
import com.example.controlrobotfragmentos.fragmentos.controlHorizontal;


public class ViewPagerAdapter extends  FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity)
    {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position)
    {
        // Retorna el fragmento correspondiente a la posición
        switch (position)
        {
            case 0:
                return new controlHorizontal();
            case 1:
                return new ControlVertical();
            case 2:
                return new ControlVoz();
            default:
                return new controlHorizontal();
        }
    }
    @Override
    public int getItemCount()
    {
        // Retorna el número total de fragmentos
        return 3;
    }
}
