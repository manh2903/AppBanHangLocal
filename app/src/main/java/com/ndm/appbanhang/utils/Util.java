package com.ndm.appbanhang.utils;

import android.os.Build;

import com.github.javafaker.Faker;
import com.ndm.appbanhang.enitities.Comment;
import com.ndm.appbanhang.enitities.Product;
import com.ndm.appbanhang.enitities.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static final User user = new User(1, "Phạm Bích Phượng",20, "09823672" ,"ha noi",  "", "user@gmail.com", "123");
    public static final List<Product> products = new ArrayList<>();

    public static List<Comment> generateFakeComments() {
        List<Comment> commentList = new ArrayList<>();

        String[] sampleSentences = {
                "Sản phẩm này thật tuyệt vời!",
                "Mình rất thích sản phẩm này.",
                "Dịch vụ giao hàng rất nhanh chóng.",
                "Chất lượng tuyệt vời, sẽ mua lại.",
                "Sản phẩm đáng tiền!"
        };

        String[] sampleNames = {
                "Nguyễn Văn A",
                "Trần Thị B",
                "Lê Quang C",
                "Phạm Thanh D",
                "Bùi Minh E"
        };

        for (int i = 1; i <= 10; i++) {
            Comment comment = new Comment();
            comment.setId(i);

            String content = sampleSentences[(int) (Math.random() * sampleSentences.length)];
            comment.setContent(content);

            String name = sampleNames[(int) (Math.random() * sampleNames.length)];
            comment.setFull_name(name);

            // Thời gian tạo là trong vòng 10 ngày gần đây
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                comment.setCreatedAt(LocalDate.now().minusDays((int) (Math.random() * 10)).toString());
            }

            commentList.add(comment);
        }

        return commentList;
    }


}
