package edu.aku.hassannaqvi.src_2.ui.form4;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;

import edu.aku.hassannaqvi.src_2.R;
import edu.aku.hassannaqvi.src_2.databinding.ActivityF4SectionABinding;
import edu.aku.hassannaqvi.src_2.validation.ClearClass;

public class F4SectionAActivity extends AppCompatActivity {

    ActivityF4SectionABinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_f4_section_a);
        bi.setCallback(this);

        setupViews();
    }

    private void setupViews() {
        bi.f4a01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId != bi.f4a01b.getId()) {
                    ClearClass.ClearAllFields(bi.fldGrpf4a02, null);
                } else {
                    ClearClass.ClearAllFields(bi.fldGrpMain, null);
                }
            }
        });

        bi.f4a08.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId != bi.f4a08a.getId()) {
                    ClearClass.ClearAllFields(bi.fldGrp0910, null);
                }
            }
        });
    }

    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
                if (UpdateDB()) {
                    startActivity(new Intent(getApplicationContext(), F4SectionBActivity.class));
                } else {
                    Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean UpdateDB() {

        return true;
    }

    private void SaveDraft() throws JSONException {
    }

    private boolean formValidation() {

        return true;
    }

    public void BtnEnd() {

    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back.", Toast.LENGTH_SHORT).show();
    }
}
