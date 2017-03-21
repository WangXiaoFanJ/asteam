package com.dev.nutclass.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dev.nutclass.R;
import com.dev.nutclass.network.OkHttpClientManager;
import com.dev.nutclass.rsa.Base64Utils;
import com.dev.nutclass.utils.BitmapUtil;
import com.dev.nutclass.utils.DensityUtil;
import com.dev.nutclass.utils.File2ByteUtils;
import com.dev.nutclass.utils.LogUtil;
import com.dev.nutclass.utils.SharedPrefUtil;
import com.dev.nutclass.view.MyPopupWindow;
import com.foamtrace.photopicker.ImageCaptureManager;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.foamtrace.photopicker.SelectModel;
import com.foamtrace.photopicker.intent.PhotoPickerIntent;
import com.foamtrace.photopicker.intent.PhotoPreviewIntent;
import com.squareup.okhttp.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WriteCommentActivityNew extends BaseActivity implements View.OnClickListener{
    private static final String TAG="WriteCommentActivityNew";
    private Context mContext;
    private TextView postImageIv;
    private static final int REQUEST_CAMERA_CODE = 11;
    private static final int REQUEST_PREVIEW_CODE = 22;
    private MyPopupWindow popWindow;
    private LinearLayout rootLinaerLayout;
    private ArrayList<String> imagePaths = null;
    private ImageCaptureManager captureManager; // 相机拍照处理类
    private GridView gridView;
    private int columnWidth;
    private GridAdapter gridAdapter;
    private TextView cancalTv,publishTv;
    private ImageView uploadingIv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = WriteCommentActivityNew.this;
        setContentView(R.layout.activity_write_comment_activity_new);
        initView();
        initGridView();
    }

    //gridview列数和列宽
    private void initGridView() {
        int cols = DensityUtil.getDisplayWidth(mContext)/ DensityUtil.getDisplayDensity(mContext);
        cols = cols < 3 ? 3 : cols;
        gridView.setNumColumns(cols);

        // Item Width
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int columnSpace = getResources().getDimensionPixelOffset(R.dimen.space_size);
        columnWidth = (screenWidth - columnSpace * (cols-1)) / cols;
    }

    private void initView() {
        rootLinaerLayout = (LinearLayout) findViewById(R.id.ll_root);
        gridView = (GridView) findViewById(R.id.gridView);
        cancalTv = (TextView) findViewById(R.id.tv_cancle);
        publishTv = (TextView) findViewById(R.id.tv_publish);
        uploadingIv = (ImageView) findViewById(R.id.iv_uploading);
        cancalTv.setOnClickListener(this);
        uploadingIv.setOnClickListener(this);
        publishTv.setOnClickListener(this);

        // preview
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhotoPreviewIntent intent = new PhotoPreviewIntent(mContext);
                intent.setCurrentItem(position);
                intent.setPhotoPaths(imagePaths);
                startActivityForResult(intent, REQUEST_PREVIEW_CODE);
            }
        });

    }

    private void showPopupWidow() {
        View rootView = null;
        rootView = LayoutInflater.from(mContext).inflate(R.layout.view_baby_gender_popup,null);
        popWindow = new MyPopupWindow(mContext,rootView);
        final TextView manGenderTv = (TextView) rootView.findViewById(R.id.tv_gender_man);
        TextView womanGenderTv = (TextView) rootView.findViewById(R.id.tv_gender_woman);
        TextView cancelTv = (TextView) rootView.findViewById(R.id.tv_cancel);
        manGenderTv.setText("拍照");
        womanGenderTv.setText("图库相册");
        manGenderTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(captureManager == null){
                        captureManager = new ImageCaptureManager(mContext);
                    }
                    Intent intent = captureManager.dispatchTakePictureIntent();
                    startActivityForResult(intent, ImageCaptureManager.REQUEST_TAKE_PHOTO);
                } catch (IOException e) {
                    Toast.makeText(mContext, com.foamtrace.photopicker.R.string.msg_no_camera, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                popWindow.dismiss();
            }
        });
        womanGenderTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPickerIntent intent = new PhotoPickerIntent(mContext);
                intent.setSelectModel(SelectModel.MULTI);
                intent.setShowCarema(true);
                intent.setMaxTotal(9); // 最多选择照片数量，默认为9
                intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
                startActivityForResult(intent, REQUEST_CAMERA_CODE);
                popWindow.dismiss();
            }
        });
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dismiss();
            }
        });
        popWindow= new MyPopupWindow(mContext,rootView);
        popWindow.setAnimationStyle(R.style.Anim_Menu_Bottombar);
        popWindow.showAtLocation(rootLinaerLayout, Gravity.BOTTOM,0,0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case REQUEST_CAMERA_CODE:
                    final ArrayList<String> paths2 = new ArrayList<>();
                    paths2.addAll(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    loadAdpater(paths2);
                    break;
                // 预览
                case REQUEST_PREVIEW_CODE:
                    loadAdpater(data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT));
                    break;
                // 调用相机拍照
                case ImageCaptureManager.REQUEST_TAKE_PHOTO:
                    if(captureManager.getCurrentPhotoPath() != null) {
                        captureManager.galleryAddPic();
                        ArrayList<String> paths = new ArrayList<>();
                        paths.add(captureManager.getCurrentPhotoPath());
                        loadAdpater(paths);
                    }
                    break;

            }
        }
    }

    private void postImageUrl(List<String> paths2) {
        String url = "https:dev.kobiko.cn/Api/Index/addCommentImage";
        LogUtil.d(TAG,"path2:"+paths2.size());
        for(int i=0;i<paths2.size();i++){
          //对文件进行压缩处理后，使用base64进行编码
            Bitmap bitmap = BitmapUtil.getimage(paths2.get(i));
            LogUtil.d(TAG,"bitmapWidth:"+bitmap.getWidth()+"height:"+bitmap.getHeight()+"byte："+bitmap.getByteCount());
            String params = Base64Utils.encode(  BitmapUtil.getBitmapbyte(bitmap));
//            LogUtil.d(TAG,"pathSize:"+params);
            Map<String,String> map = new HashMap<>();
            map.put("headImage",params);
            OkHttpClientManager.postAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
                @Override
                public void onError(Request request, Exception e) {
                    LogUtil.d(TAG,"error:"+e.getMessage());
                }

                @Override
                public void onResponse(String response) {
                    LogUtil.d(TAG,"response"+response);
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String status = jsonObject.optString("status");
                        if(status.equals("1")){
                            JSONObject dataObj = jsonObject.optJSONObject("data");
                            String url = dataObj.optString("image_path");
//                            imageUrlLists.add(url);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            },map);
        }
    }

    private void loadAdpater(ArrayList<String> paths){
        if(imagePaths == null){
            imagePaths = new ArrayList<>();
        }
        imagePaths.clear();
        imagePaths.addAll(paths);
//        imagePaths.add("https://dev.kobiko.cn/static/Uploads/2017-01-11/5875a8f62b402.png");

        try{
            JSONArray obj = new JSONArray(imagePaths);
            Log.e("--", obj.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

        if(gridAdapter == null){
            gridAdapter = new GridAdapter(imagePaths);
            gridView.setAdapter(gridAdapter);
        }else {
            gridAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        if(v==cancalTv){
            finish();
        }else if (v==publishTv){

        }else if (v==uploadingIv){
            showPopupWidow();
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    private class GridAdapter extends BaseAdapter {
        private ArrayList<String> listUrls;

        public GridAdapter(ArrayList<String> listUrls) {
            this.listUrls = listUrls;
        }

        @Override
        public int getCount() {
            return listUrls.size();
        }

        @Override
        public String getItem(int position) {
            return listUrls.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.item_image, null);
                imageView = (ImageView) convertView.findViewById(R.id.imageView);
                convertView.setTag(imageView);
                // 重置ImageView宽高
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(columnWidth, columnWidth);
                imageView.setLayoutParams(params);
            }else {
                imageView = (ImageView) convertView.getTag();
            }
            Glide.with(mContext)
                    .load(new File(getItem(position)))
                    .placeholder(R.mipmap.default_error)
                    .error(R.mipmap.default_error)
                    .centerCrop()
                    .crossFade()
                    .into(imageView);
            if(imageView.getDrawable()!=null){
                Log.d("===","width:"+imageView.getDrawable().getBounds().width()+"height:"+
                        imageView.getDrawable().getBounds().height());
            }
            return convertView;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        List<String> list = new ArrayList<>();
        list.clear();
    }
}
