package riis.bankjoy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PayeeListAdapter extends RecyclerView.Adapter<PayeeListAdapter.PayeeViewHolder>
{
    private ArrayList<Payee> mPayeeList;
    private Context mContext;

    public PayeeListAdapter(Context context, ArrayList<Payee> payeeList)
    {
        mPayeeList = payeeList;
        mContext = context;
    }

    @Override
    public PayeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        TextView view = (TextView) LayoutInflater.from(mContext)
                .inflate(R.layout.payee_list_item, parent, false);
        return new PayeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PayeeViewHolder holder, final int position)
    {
        ((TextView) holder.itemView).setText(mPayeeList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(mContext, PayeeDetailActivity.class);
                intent.putExtra("payeeId", mPayeeList.get(position).getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mPayeeList.size();
    }

    static class PayeeViewHolder extends RecyclerView.ViewHolder
    {
        public PayeeViewHolder(TextView itemView)
        {
            super(itemView);
        }
    }
}
