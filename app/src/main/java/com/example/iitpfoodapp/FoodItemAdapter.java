

        package com.example.iitpfoodapp;

        import android.app.Activity;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.Filter;
        import android.widget.Filterable;
        import android.widget.TextView;

        import java.util.ArrayList;

public class FoodItemAdapter extends ArrayAdapter<FoodItem> implements Filterable {

    Context context;
    ArrayList<FoodItem> foodItemArrayList;
    ArrayList<FoodItem> orig;

    public FoodItemAdapter(Activity context, ArrayList<FoodItem> Wo) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, Wo);
        this.context = context;
        this.foodItemArrayList = Wo;
    }

    public class FoodItemHolder
    {
        TextView fname;
        TextView fprice;
    }


    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<FoodItem> results = new ArrayList<FoodItem>();
                if (orig == null)
                    orig = foodItemArrayList;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final FoodItem g : orig) {
                            if (g.getFoodName().toLowerCase()
                                    .contains(constraint.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                foodItemArrayList = (ArrayList<FoodItem>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return foodItemArrayList.size();
    }

    @Override
    public FoodItem getItem(int position) {
        return foodItemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.food_items, parent, false);
        }

        FoodItem currentAndroidFlavor=getItem(position);
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.foodName);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText(currentAndroidFlavor.getFoodName());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.foodPrice);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        numberTextView.setText(currentAndroidFlavor.getFoodPrice());
        return listItemView;

    }
}

