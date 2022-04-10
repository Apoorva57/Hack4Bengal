package com.example.hack4bengal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditCourseActivity extends AppCompatActivity {

    private TextInputEditText courseNameEdt, coursePriceEdt, courseSuitedForEdt, courseImgEdt, courseLinkEdt, courseDescEdt;
    private Button updateCourseBtn, deleteCourseBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String courseID;
    private CourseRVModel courseRVModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        firebaseDatabase = FirebaseDatabase.getInstance();
        courseNameEdt = findViewById(R.id.idEdtCourseName);
        coursePriceEdt = findViewById(R.id.idEdtCoursePrice);
        courseSuitedForEdt = findViewById(R.id.idEdtCourseSuitedFor);
        courseImgEdt = findViewById(R.id.idEdtCourseImageLink);
        courseLinkEdt = findViewById(R.id.idEdtCourseLink);
        courseDescEdt = findViewById(R.id.idEdtCourseDescription);
        updateCourseBtn = findViewById(R.id.idBtnUpdateCourse);
        deleteCourseBtn = findViewById(R.id.idBtnDeleteCourse);
        loadingPB = findViewById(R.id.idPBLoading);
        courseRVModel = getIntent().getParcelableExtra("course");
        if(courseRVModel!=null){
            courseNameEdt.setText(courseRVModel.getCourseName());
            coursePriceEdt.setText(courseRVModel.getCoursePrice());
            courseSuitedForEdt.setText(courseRVModel.getCourseSuitedFor());
            courseImgEdt.setText(courseRVModel.getCourseImg());
            courseLinkEdt.setText(courseRVModel.getCourseLink());
            courseDescEdt.setText(courseRVModel.getCourseDescription());
            courseID = courseRVModel.getCourseID();
        }

        databaseReference = firebaseDatabase.getReference("Courses").child(courseID);
        updateCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingPB.setVisibility(View.VISIBLE);
                String courseName = courseNameEdt.getText().toString();
                String coursePrice = coursePriceEdt.getText().toString();
                String courseSuitedFor = courseSuitedForEdt.getText().toString();
                String courseImg = courseImgEdt.getText().toString();
                String courseLink = courseLinkEdt.getText().toString();
                String courseDesc = courseDescEdt.getText().toString();

                Map<String,Object> map = new HashMap<>();
                map.put("courseName",courseName);
                map.put("coursePrice",coursePrice);
                map.put("courseSuitedFor",courseSuitedFor);
                map.put("courseImg",courseImg);
                map.put("courseLink",courseLink);
                map.put("courseDesc",courseDesc);
                map.put("courseID",courseID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.updateChildren(map);
                        Toast.makeText(EditCourseActivity.this, "Course Updated..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditCourseActivity.this,MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(EditCourseActivity.this, "Failed to update course..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        deleteCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCourse();
            }
        });
    }
    private void deleteCourse(){
        databaseReference.removeValue();
        Toast.makeText(this, "Course Deleted..", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditCourseActivity.this,MainActivity.class));
    }
}