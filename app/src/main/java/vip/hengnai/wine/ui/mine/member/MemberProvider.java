package vip.hengnai.wine.ui.mine.member;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewProvider;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.IntegralEntity;
import vip.hengnai.wine.entity.MemberEntity;

/**
 * 发票provider
 *
 * @author Hugh
 */
public class MemberProvider extends ItemViewProvider<MemberEntity, MemberProvider.ProviderHolder> {




    public MemberProvider() {

    }


    @NonNull
    @Override
    protected ProviderHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ProviderHolder(inflater.inflate(R.layout.member_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ProviderHolder holder, @NonNull MemberEntity memberEntity) {


    }

    public class ProviderHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_icon)
        ImageView imgIcon;
        @BindView(R.id.tx_content)
        TextView txContent;
        @BindView(R.id.tx_dis)
        TextView txDis;

        public ProviderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
