package edu.aku.hassannaqvi.src_2.ui.form9;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.src_2.R;
import edu.aku.hassannaqvi.src_2.core.DatabaseHelper;
import edu.aku.hassannaqvi.src_2.core.MainApp;
import edu.aku.hassannaqvi.src_2.databinding.ActivityF9SectionBBinding;
import edu.aku.hassannaqvi.src_2.other.JsonUtils;
import edu.aku.hassannaqvi.src_2.ui.EndingActivity;
import edu.aku.hassannaqvi.src_2.validation.ClearClass;
import edu.aku.hassannaqvi.src_2.validation.ValidatorClass;

public class F9SectionBActivity extends AppCompatActivity {

    ActivityF9SectionBBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_f9_section_b);
        bi.setCallback(this);
        setTitle(R.string.f9bHeading);
        setupViews();
    }

    private void setupViews() {

        bi.f9b01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId != bi.f9b01a.getId()) {
                    ClearClass.ClearAllFields(bi.fldGrpMain, null);
                }
            }
        });

        bi.f9b03.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId != bi.f9b03a.getId()) {
                    ClearClass.ClearAllFields(bi.fldGrp45678, null);
                }
            }
        });
        bi.f9b05.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId != bi.f9b05a.getId()) {
                    ClearClass.ClearAllFields(bi.fldGrpf9b06, null);
                }
            }
        });

        bi.f9b07.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId != bi.f9b07a.getId()) {
                    ClearClass.ClearAllFields(bi.fldGrpf9b08, null);
                }
            }
        });

        bi.f9b09.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == bi.f9b09b.getId()) {
                    ClearClass.ClearAllFields(bi.fldGrpf9b10, null);
                }
            }
        });
        bi.f9b11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == bi.f9b11b.getId()) {
                    ClearClass.ClearAllFields(bi.fldGrpf9b12, null);
                }
            }
        });
    }

    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
                if (UpdateDB()) {
                    finish();
                    startActivity(new Intent(getApplicationContext(),
                            EndingActivity.class).putExtra("complete", true));
                } else {
                    Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean UpdateDB() {

        DatabaseHelper db = new DatabaseHelper(this);

        // 2. UPDATE FORM ROWID
        int updcount = db.updatesF9();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {


        JSONObject f1 = new JSONObject();
        f1.put("f9b01", bi.f9b01a.isChecked() ? "1" : bi.f9b01b.isChecked() ? "2" : "0");
        f1.put("f9b02", bi.f9b02.getText().toString());
        f1.put("f9b03", bi.f9b03a.isChecked() ? "1" : bi.f9b03b.isChecked() ? "2" : "0");
        f1.put("f9b04", bi.f9b04.getText().toString());
        f1.put("f9b05", bi.f9b05a.isChecked() ? "1" : bi.f9b05b.isChecked() ? "2" : "0");
        f1.put("f9b06", bi.f9b06.getText().toString());
        f1.put("f9b07", bi.f9b07a.isChecked() ? "1" : bi.f9b07b.isChecked() ? "2" : "0");
        f1.put("f9b08", bi.f9b08.getText().toString());
        f1.put("f9b09", bi.f9b09a.isChecked() ? "1" : bi.f9b09b.isChecked() ? "2" : "0");
        f1.put("f9b10", bi.f9b10.getText().toString());
        f1.put("f9b11", bi.f9b11a.isChecked() ? "1" : bi.f9b11b.isChecked() ? "2" : "0");
        f1.put("f9b12", bi.f9b12.getText().toString());


        JSONObject merged = JsonUtils.mergeJSONObjects(new JSONObject(MainApp.fc.getF9()), f1);
        MainApp.fc.setF9(String.valueOf(merged));


    }

    private boolean formValidation() {

        if (!ValidatorClass.EmptyCheckingContainer(this, bi.fldGrpF9B)) {
            return false;
        }

        if (bi.fldGrpMain.getVisibility() != View.GONE) {

            if (bi.f9b03a.isChecked()) {
                if (Integer.parseInt(bi.f9b04.getText().toString()) > Integer.parseInt(bi.f9b02.getText().toString())) {
                    bi.f9b04.setError("Can not be greater than " + Integer.parseInt(bi.f9b02.getText().toString()));
                    bi.f9b04.requestFocus();
                    return false;
                } else {
                    bi.f9b04.setError(null);
                    bi.f9b04.clearFocus();
                }
            }

            if (bi.f9b05a.isChecked()) {
                if (Integer.parseInt(bi.f9b06.getText().toString()) > Integer.parseInt(bi.f9b04.getText().toString())) {
                    bi.f9b06.setError("Can not be greater than " + Integer.parseInt(bi.f9b04.getText().toString()));
                    bi.f9b06.requestFocus();
                    return false;
                } else {
                    bi.f9b06.setError(null);
                    bi.f9b06.clearFocus();
                }
            }

            if (bi.f9b07a.isChecked()) {
                if (Integer.parseInt(bi.f9b08.getText().toString()) > Integer.parseInt(bi.f9b04.getText().toString())) {
                    bi.f9b08.setError("Can not be greater than " + Integer.parseInt(bi.f9b04.getText().toString()));
                    bi.f9b08.requestFocus();
                    return false;
                } else {
                    bi.f9b08.setError(null);
                    bi.f9b08.clearFocus();
                }
            }

        }

        return true;
    }

    public void BtnEnd() {

        MainApp.endActivity(this, this);
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back.", Toast.LENGTH_SHORT).show();
    }
}
