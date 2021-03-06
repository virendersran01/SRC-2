package edu.aku.hassannaqvi.src_2.ui.form3;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.src_2.R;
import edu.aku.hassannaqvi.src_2.core.DatabaseHelper;
import edu.aku.hassannaqvi.src_2.core.MainApp;
import edu.aku.hassannaqvi.src_2.databinding.ActivityF3SectionA02Binding;
import edu.aku.hassannaqvi.src_2.other.JsonUtils;
import edu.aku.hassannaqvi.src_2.ui.form4.F4SectionAActivity;
import edu.aku.hassannaqvi.src_2.ui.form9.F9SectionAActivity;
import edu.aku.hassannaqvi.src_2.validation.ClearClass;
import edu.aku.hassannaqvi.src_2.validation.ValidatorClass;

public class F3SectionA02Activity extends AppCompatActivity {

    ActivityF3SectionA02Binding bi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_f3_section_a02);
        bi.setCallback(this);

        setTitle(R.string.f3Heading);

        setupViews();
    }

    private void setupViews() {

        bi.f3a30e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    ClearClass.ClearAllFields(bi.f3a30check, false);
                    bi.fldGrpf3a31.setVisibility(View.GONE);
                    ClearClass.ClearAllFields(bi.fldGrpf3a31, null);
                } else {
                    ClearClass.ClearAllFields(bi.f3a30check, true);
                    bi.fldGrpf3a31.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.f3a32.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == bi.f3a32b.getId() || checkedId == bi.f3a3298.getId()) {
                    ClearClass.ClearAllFields(bi.fldGrpf3a33, null);
                } else if (checkedId == bi.f3a32a.getId()) {
                    ClearClass.ClearAllFields(bi.fldGrpf3a34, null);
                }
            }
        });

        bi.f3a2399.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    bi.f3a2397.setEnabled(false);
                    ClearClass.ClearAllFields(bi.f3a23check, false);
                } else {
                    ClearClass.ClearAllFields(bi.f3a23check, true);
                    bi.f3a2397.setEnabled(true);
                }
            }
        });
        bi.f3a2397.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    bi.f3a2399.setEnabled(false);
                    ClearClass.ClearAllFields(bi.f3a23check, false);
                } else {
                    ClearClass.ClearAllFields(bi.f3a23check, true);
                    bi.f3a2399.setEnabled(true);
                }
            }
        });

        bi.f3a2899.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    ClearClass.ClearAllFields(bi.f3a28check, false);
                } else {
                    ClearClass.ClearAllFields(bi.f3a28check, true);
                }
            }
        });

        bi.f3a27.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.f3a27b.getId()) {
                    ClearClass.ClearAllFields(bi.fldGrpf3a28, null);
                } else {
                    ClearClass.ClearAllFields(bi.fldGrpf3a29, null);
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
                            MainApp.Respondent_is_UnMarried ? F9SectionAActivity.class : F4SectionAActivity.class));
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
        int updcount = db.updatesF3();

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


        f1.put("f3a1896x", bi.f3a1896x.getText().toString());
        f1.put("f3a18", bi.f3a18a.isChecked() ? "1"
                : bi.f3a18b.isChecked() ? "2"
                : bi.f3a18c.isChecked() ? "3"
                : bi.f3a18d.isChecked() ? "4"
                : bi.f3a1896.isChecked() ? "96"
                : bi.f3a1899.isChecked() ? "99"
                : "0");
        f1.put("f3a1996x", bi.f3a1996x.getText().toString());
        f1.put("f3a19", bi.f3a19a.isChecked() ? "1"
                : bi.f3a19b.isChecked() ? "2"
                : bi.f3a1998.isChecked() ? "98"
                : bi.f3a1996.isChecked() ? "96"
                : "0");
        f1.put("f3a20", bi.f3a20.getText().toString());
        f1.put("f3a21", bi.f3a21a.isChecked() ? "1"
                : bi.f3a21b.isChecked() ? "2"
                : bi.f3a2198.isChecked() ? "98"
                : "0");
        f1.put("f3a22", bi.f3a22.getText().toString());
        f1.put("f3a23a", bi.f3a23a.isChecked() ? "1" : "0");
        f1.put("f3a23b", bi.f3a23b.isChecked() ? "2" : "0");
        f1.put("f3a23c", bi.f3a23c.isChecked() ? "3" : "0");
        f1.put("f3a23d", bi.f3a23d.isChecked() ? "4" : "0");
        f1.put("f3a23e", bi.f3a23e.isChecked() ? "5" : "0");
        f1.put("f3a23f", bi.f3a23f.isChecked() ? "6" : "0");
        f1.put("f3a23g", bi.f3a23g.isChecked() ? "7" : "0");
        f1.put("f3a23h", bi.f3a23h.isChecked() ? "8" : "0");
        f1.put("f3a23i", bi.f3a23i.isChecked() ? "9" : "0");
        f1.put("f3a23j", bi.f3a23j.isChecked() ? "10" : "0");
        f1.put("f3a2396", bi.f3a2396.isChecked() ? "96" : "0");
        f1.put("f3a2396x", bi.f3a2396x.getText().toString());
        f1.put("f3a2397", bi.f3a2397.isChecked() ? "97" : "0");
        f1.put("f3a2399", bi.f3a2399.isChecked() ? "99" : "0");
        f1.put("f3a24", bi.f3a24a.isChecked() ? "1" : bi.f3a24b.isChecked() ? "2" : bi.f3a2499.isChecked() ? "99" : "0");
        f1.put("f3a25", bi.f3a25a.isChecked() ? "1" : bi.f3a25b.isChecked() ? "2" : bi.f3a2598.isChecked() ? "98" : "0");
        f1.put("f3a26", bi.f3a26.getText().toString());
        f1.put("f3a27", bi.f3a27a.isChecked() ? "1" : bi.f3a27b.isChecked() ? "2" : bi.f3a2798.isChecked() ? "98" : "0");

        f1.put("f3a2896x", bi.f3a2896x.getText().toString());
        f1.put("f3a28a", bi.f3a28a.isChecked() ? "1" : "0");
        f1.put("f3a28b", bi.f3a28b.isChecked() ? "2" : "0");
        f1.put("f3a28c", bi.f3a28c.isChecked() ? "3" : "0");
        f1.put("f3a28d", bi.f3a28d.isChecked() ? "4" : "0");
        f1.put("f3a28e", bi.f3a28e.isChecked() ? "5" : "0");
        f1.put("f3a28f", bi.f3a28f.isChecked() ? "6" : "0");
        f1.put("f3a28g", bi.f3a28g.isChecked() ? "7" : "0");
        f1.put("f3a28h", bi.f3a28h.isChecked() ? "8" : "0");
        f1.put("f3a28i", bi.f3a28i.isChecked() ? "9" : "0");
        f1.put("f3a28j", bi.f3a28j.isChecked() ? "10" : "0");
        f1.put("f3a2899", bi.f3a2899.isChecked() ? "99" : "0");
        f1.put("f3a2896", bi.f3a2896.isChecked() ? "96" : "0");


        f1.put("f3a29", bi.f3a29a.isChecked() ? "1" : bi.f3a29b.isChecked() ? "2"
                : bi.f3a2999.isChecked() ? "99" : "0");
        f1.put("f3a30a", bi.f3a30a.isChecked() ? "1" : "0");
        f1.put("f3a30b", bi.f3a30b.isChecked() ? "2" : "0");
        f1.put("f3a30c", bi.f3a30c.isChecked() ? "3" : "0");
        f1.put("f3a30d", bi.f3a30d.isChecked() ? "4" : "0");
        f1.put("f3a3096", bi.f3a3096.isChecked() ? "96" : "0");
        f1.put("f3a3096x", bi.f3a3096x.getText().toString());
        f1.put("f3a30e", bi.f3a30e.isChecked() ? "5" : "0");
        f1.put("f3a31a", bi.f3a31a.isChecked() ? "1" : "0");
        f1.put("f3a31b", bi.f3a31b.isChecked() ? "2" : "0");
        f1.put("f3a31c", bi.f3a31c.isChecked() ? "3" : "0");
        f1.put("f3a31d", bi.f3a31d.isChecked() ? "4" : "0");
        f1.put("f3a3196", bi.f3a3196.isChecked() ? "96" : "0");
        f1.put("f3a3196x", bi.f3a3196x.getText().toString());
        f1.put("f3a32", bi.f3a32a.isChecked() ? "1"
                : bi.f3a32b.isChecked() ? "2"
                : bi.f3a3298.isChecked() ? "98"
                : "0");
        f1.put("f3a3396x", bi.f3a3396x.getText().toString());
        f1.put("f3a33a", bi.f3a33a.isChecked() ? "1" : "0");
        f1.put("f3a33b", bi.f3a33b.isChecked() ? "2" : "0");
        f1.put("f3a33c", bi.f3a33c.isChecked() ? "3" : "0");
        f1.put("f3a3396", bi.f3a3396.isChecked() ? "96" : "0");
        f1.put("f3a3396x", bi.f3a3396x.getText().toString());
        f1.put("f3a34", bi.f3a34.getText().toString());


        JSONObject merged = JsonUtils.mergeJSONObjects(new JSONObject(MainApp.fc.getF3()), f1);
        MainApp.fc.setF3(String.valueOf(merged));


    }

    private boolean formValidation() {

        return ValidatorClass.EmptyCheckingContainer(this, bi.fdlGrpSectionF3);
    }

    public void BtnEnd() {

        MainApp.endActivity(this, this);
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back.", Toast.LENGTH_SHORT).show();
    }
}
