package com.demo.assignment_priyanka.Adapter

import android.app.AlertDialog
import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.demo.assignment_priyanka.Database.UserModelRoomDb
import com.demo.assignment_priyanka.databinding.UserLayoutBinding


class UserAdapter(private val context : Context,
                  private val mList : List<UserModelRoomDb>
                  ): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    inner class ViewHolder(private val mbinding: UserLayoutBinding) : RecyclerView.ViewHolder(mbinding.root)
    {
        fun setData(position: Int)
        {
            mbinding.txtName.text = mList[position].name
            mbinding.txtEmail.text = mList[position].email
            mbinding.txtPhone.text = mList[position].phone
            mbinding.txtAddress.text = mList[position].address


        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        return  ViewHolder(UserLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
       holder.setData(position)
    }

    override fun getItemCount(): Int {
        return  mList.size
    }

}