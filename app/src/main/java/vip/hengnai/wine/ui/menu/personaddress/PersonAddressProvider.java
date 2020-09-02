package vip.hengnai.wine.ui.menu.personaddress;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewProvider;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.PersonAddressEntity;

/**
 * @author Viiliz
 * @date 2019/11/14.
 * GitHub：
 * email：
 * description：
 */
public class PersonAddressProvider extends ItemViewProvider<PersonAddressEntity, PersonAddressProvider.ProviderHolder> {



    private Context mContext;

    public PersonAddressProvider(Context mContext) {
        this.mContext = mContext;
    }


    private OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public interface OnClickListener {
        /**
         * 点击事件
         *
         * @param personAddressEntity
         * @param
         */
        void onItemClick(PersonAddressEntity personAddressEntity);

    }


    @NonNull
    @Override
    protected ProviderHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ProviderHolder(inflater.inflate(R.layout.person_address_item_layout, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ProviderHolder holder, @NonNull PersonAddressEntity personAddressEntity) {
        //收件人抬头
        holder.txName.setText(personAddressEntity.getTitle());
        holder.txAddress.setText(personAddressEntity.getAddress() + "(" + personAddressEntity.getRoom() + ")");
        holder.txPhone.setText(personAddressEntity.getPhone());
        holder.txAddressFor.setText(personAddressEntity.getTag());
        if(personAddressEntity.isDefaultAddress()){
            holder.txAddressDefault.setVisibility(View.VISIBLE);
        }else{
            holder.txAddressDefault.setVisibility(View.GONE);
        }
        holder.imgModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickListener.onItemClick(personAddressEntity);
            }
        });
    }

    public class ProviderHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tx_name)
        TextView txName;
        @BindView(R.id.tx_phone)
        TextView txPhone;
        @BindView(R.id.tx_address_for)
        TextView txAddressFor;
        @BindView(R.id.tx_address_default)
        TextView txAddressDefault;
        @BindView(R.id.ll_info)
        LinearLayout llInfo;
        @BindView(R.id.tx_address)
        TextView txAddress;
        @BindView(R.id.img_modify)
        ImageView imgModify;
        public ProviderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
