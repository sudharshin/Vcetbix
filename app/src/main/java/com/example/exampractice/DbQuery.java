
        package com.example.exampractice;

        import static com.google.android.material.color.utilities.MaterialDynamicColors.error;

        import android.util.ArrayMap;
        import androidx.annotation.NonNull;
        import com.google.android.gms.tasks.OnFailureListener;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.firestore.DocumentReference;
        import com.google.firebase.firestore.FirebaseFirestore;
        import com.google.firebase.firestore.FieldValue;
        import com.google.firebase.firestore.QueryDocumentSnapshot;
        import com.google.firebase.firestore.QuerySnapshot;
        import com.google.firebase.firestore.WriteBatch;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Map;

public class DbQuery {
    public static FirebaseFirestore g_firestore;
    //public static List<CategoryModel> g_catList = new ArrayList<>();

    public static void createUserData(String email, String name, final MyCompleteListener myCompleteListener) {
        Map<String, Object> userData = new ArrayMap<>();
        userData.put("EMAIL_ID", email);
        userData.put("NAME", name);
        userData.put("TOTAL_SCORE", 0);
        DocumentReference userDoc = g_firestore.collection("USERS").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        WriteBatch batch = g_firestore.batch();
        batch.set(userDoc, userData);
        DocumentReference countDoc = g_firestore.collection("USERS").document("TOTAL_USERS");
        batch.update(countDoc, "COUNT", FieldValue.increment(1));
        batch.commit()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        myCompleteListener.onSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        myCompleteListener.onFailure();
                    }
                });

    }


}