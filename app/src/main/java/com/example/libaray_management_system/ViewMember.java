package com.example.libaray_management_system;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class ViewMember extends AppCompatActivity implements MemberAdapter.OnMemberClickListener {
    private static final int UPDATE_MEMBER_REQUEST = 1;

    private RecyclerView recyclerView;
    private MemberAdapter memberAdapter;
    private List<Member> memberList;
    private Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_member);

        recyclerView = findViewById(R.id.recyclerView3);
        memberList = new ArrayList<>();
        dbHelper = new Database(this);

        fetchMembers();

        memberAdapter = new MemberAdapter(memberList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(memberAdapter);
    }

    private void fetchMembers() {
        memberList.clear();
        memberList.addAll(dbHelper.getAllMember());
    }

    @Override
    public void onUpdateClick(int position) {
        Member member = memberList.get(position);
        Intent intent = new Intent(this, MemberUp.class);
        intent.putExtra("card_id", member.getId());
        startActivityForResult(intent, UPDATE_MEMBER_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UPDATE_MEMBER_REQUEST && resultCode == RESULT_OK && data != null) {
            int cardID = data.getIntExtra("card_id", -1);
            String updatedName = data.getStringExtra("updated_Name");
            String updatedAddress = data.getStringExtra("updated_Address");
            String updatedPhone = data.getStringExtra("updated_Phone");
            String updatedUnpaid = data.getStringExtra("updated_Unpaid");
            if (cardID != -1) {
                for (Member member : memberList) {
                    if (member.getId() == cardID) {
                        member.setName(updatedName);
                        member.setAddress(updatedAddress);
                        member.setPhone(updatedPhone);
                        member.setUnpaid(updatedUnpaid);
                        dbHelper.updateMember(cardID, updatedName, updatedAddress, updatedPhone, updatedUnpaid);
                        break;
                    }
                }
                memberAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Member updated successfully", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onMemberClick(int position) {
        Member member = memberList.get(position);
        Toast.makeText(this, "Clicked Member: " + member.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {
        Member member = memberList.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this member?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean deletionResult = dbHelper.deleteMember(member.getId());
                        if (deletionResult) {
                            memberList.remove(position);
                            memberAdapter.notifyItemRemoved(position);
                            Toast.makeText(ViewMember.this, "Member deleted successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ViewMember.this, "Failed to delete member", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
