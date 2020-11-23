package com.merrix.multiselectrecyclerview;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class MultiSelectAdapter extends RecyclerView.Adapter {

    private List itemList = new ArrayList();

    // Item indexes for deselecting items :
    private List<Integer> indexList = new ArrayList<>();


    public void selectItem(int position, MultiSelectAdapter rightAdapter) {

        Object selectedItem = itemList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, position+20 >= getItemCount()?getItemCount():position+20);
        rightAdapter.selectItemR(selectedItem, position);

    }

    private void selectItemR(Object object, int position)
    {
        itemList.add(object);
        indexList.add(position);
        notifyItemInserted(itemList.size()-1);
//        relations.put(itemList.size()-1, position);
    }
    private void deselectItemL(int position, Object object)
    {
        itemList.add(position, object);
        notifyItemInserted(position);
    }




    public void deselectItem(int position, MultiSelectAdapter leftAdapter)
    {
        int index = computeIndex(position);

        leftAdapter.deselectItemL(index, itemList.remove(position));
        indexList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, position+20 >= getItemCount()?getItemCount():position+20);
    }

    private int computeIndex(int position){

        int lessIndexCount = 0;
        for (int i = position+1; i < indexList.size(); i++) {
            if(indexList.get(position) <= indexList.get(i))
            {
                indexList.set(i, indexList.get(i)+1);
            }
            if(indexList.get(position) > indexList.get(i))
            {
                lessIndexCount++;
            }
        }
        return (indexList.get(position) - lessIndexCount >= 0? indexList.get(position) - lessIndexCount: 0);
    }


    public void setItemList(List itemList)
    {
        this.itemList = itemList;
    }



    public List getItems()
    {
        return itemList;
    }

    public int getItemsSize()
    {
        return itemList.size();
    }


}
