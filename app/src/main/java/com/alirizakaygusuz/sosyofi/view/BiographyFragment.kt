package com.alirizakaygusuz.sosyofi.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.alirizakaygusuz.sosyofi.R
import com.alirizakaygusuz.sosyofi.databinding.FragmentBiographyBinding
import com.alirizakaygusuz.sosyofi.databinding.FragmentUserMainBinding
import com.alirizakaygusuz.sosyofi.model.User
import com.alirizakaygusuz.sosyofi.service.SosyofiAPIReply
import com.alirizakaygusuz.sosyofi.util.SosyofiAPIUtils
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import java.lang.Exception
import java.util.jar.Manifest


class BiographyFragment : Fragment() {

    private lateinit var binding: FragmentBiographyBinding
    private var sosyofiAPI = SosyofiAPIUtils.getSosyofiAPI()

    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private var selectedBitmap: Bitmap? = null

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentBiographyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerLauncher()


        arguments?.let {
            user = BiographyFragmentArgs.fromBundle(
                it
            ).user
        }




        initBiographyFields()


        binding.imvBioUser.setOnClickListener { view ->
            context?.let { context ->
                selectImage(view, context)

            }
        }

        binding.btnBioDeleteUser.setOnClickListener {
            click_btnBiodeleteUser(it)
        }

        binding.btnBioUpdateUser.setOnClickListener {
            click_btnBioUpdateUser(it)
        }

    }


    fun initBiographyFields() {
        binding.txtBioInfo.setText(user.bio, TextView.BufferType.EDITABLE)
        binding.txtBioTwitch.setText(user.twitch, TextView.BufferType.EDITABLE)
        binding.txtBioInstagram.setText(user.instagram, TextView.BufferType.EDITABLE)
        binding.txtBioTwitter.setText(user.twitter, TextView.BufferType.EDITABLE)
        binding.txtBioUnsplash.setText(user.unsplash, TextView.BufferType.EDITABLE)

        //karakrter karakater al alt alta yazdır

        val separateNickname = user.nickname.substring(0, user.nickname.length)


        var greetNickname = "M\ne\nr\nh\na\nb\na\n\n\n"
        for (element in separateNickname) {
            greetNickname += "\n${element}"
        }

        //Null Kontrolü
        binding.txtBioFollowed.text = user.followed_count.toString()
        binding.txtBioFollowers.text = user.followers_count.toString()
        binding.txtBioRight.text = ""
        binding.txtBioRight.text = greetNickname
    }

    fun updateUserBio(user: User) {

        sosyofiAPI.userUpdate(user.user_id,
            user.bio!!,
            user.instagram!!,
            user.twitch!!,
            user.twitter!!,
            user.unsplash!!).enqueue(object :
            Callback<SosyofiAPIReply> {
            override fun onResponse(
                call: Call<SosyofiAPIReply>?,
                response: retrofit2.Response<SosyofiAPIReply>?,
            ) {
                if (response != null) {
                    val responseBody = response.body()

                    if (responseBody?.success == 1) {
                        Toast.makeText(context,
                            "Güncelleme işlemi tamamlanmıştır!!",
                            Toast.LENGTH_SHORT).show()
                        val intent = Intent(context, UserMainActivity::class.java)
                        intent.putExtra("userId", user.user_id)
                        startActivity(intent)
                    } else {
                        Toast.makeText(context,
                            "Biyogrofi güncelleme işlemi başarısız!!",
                            Toast.LENGTH_LONG).show()
                    }
                }

            }

            override fun onFailure(call: Call<SosyofiAPIReply>?, t: Throwable?) {
                Log.e("BiographyActivty Update Error:", t?.message.toString())
            }

        })


    }

    fun click_btnBioUpdateUser(view: View) {
        user.bio = binding.txtBioInfo.text.toString()
        user.twitch = binding.txtBioTwitch.text.toString()
        user.instagram = binding.txtBioInstagram.text.toString()
        user.twitter = binding.txtBioTwitter.text.toString()
        user.unsplash = binding.txtBioUnsplash.text.toString()

        Log.i("twitter:", user.twitter.toString())

        updateUserBio(user)
    }


    fun click_btnBiodeleteUser(view: View) {
        Log.i("Silinecek id:", user.user_id.toString())

        sosyofiAPI.userDelete(user.user_id).enqueue(object : Callback<SosyofiAPIReply> {
            override fun onResponse(
                call: Call<SosyofiAPIReply>?,
                response: retrofit2.Response<SosyofiAPIReply>?,
            ) {
                if (response != null) {
                    if (response.body()?.success == 1) {
                        Toast.makeText(context, "Hesabınızı silinmiştir", Toast.LENGTH_SHORT).show()
                        val intent = Intent(context, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(context,
                            "Hesabınızı silme işlemi başarısız!!",
                            Toast.LENGTH_LONG).show()
                    }

                }
            }

            override fun onFailure(call: Call<SosyofiAPIReply>?, t: Throwable?) {
                Log.e("BiographyActivty Delete Error:", t?.message.toString())
            }


        })

    }

    fun selectImage(view: View, context: Context) {
        if (ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        ) {
            //permission Granted
            val intentToGallery =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            activityResultLauncher.launch(intentToGallery)


        } else {
            //permission Denied
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)
            ) {
                //rationale
                Snackbar.make(binding.root,
                    "Permission needed for gallery",
                    Snackbar.LENGTH_INDEFINITE).setAction("Give Permission", View.OnClickListener {
                    //request permisson
                    permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)

                }).show()


            } else {
                //request permisson
                permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)

            }
        }
    }


    private fun registerLauncher() {

        //ActiviyuResultLauncher
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

                if (result.resultCode == AppCompatActivity.RESULT_OK) {
                    val intentFromResult = result.data

                    if (intentFromResult != null) {

                        var imageUri = intentFromResult.data

                        if (imageUri != null) {
                            try {
                                if (Build.VERSION.SDK_INT >= 28) {
                                    val imageSource =
                                        ImageDecoder.createSource(requireActivity().contentResolver,
                                            imageUri)
                                    selectedBitmap = ImageDecoder.decodeBitmap(imageSource)
                                    binding.imvBioUser.setImageBitmap(selectedBitmap)

                                } else {

                                    selectedBitmap =
                                        MediaStore.Images.Media.getBitmap(requireActivity().contentResolver,
                                            imageUri)
                                    binding.imvBioUser.setImageBitmap(selectedBitmap)


                                }


                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }


                    }
                }
            }


        //PermissionLauncher
        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->

                if (result) {
                    //permission granted
                    val intentToGallery =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    activityResultLauncher.launch(intentToGallery)

                } else {
                    //permissin denied
                    Toast.makeText(context, "İzin Gerekli!", Toast.LENGTH_LONG)
                        .show()
                }
            }
    }


}