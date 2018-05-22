package cn.studyhuang.cook.view;

import cn.studyhuang.cook.pojo.Evaluate;

/**
 * Created by huang on 2018/5/2.
 */

public interface PingjiaView {

        void onGetComment(Evaluate comment);
        void onFailed(int code);


}
