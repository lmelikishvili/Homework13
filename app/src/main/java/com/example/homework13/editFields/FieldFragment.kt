package com.example.homework13.editFields

import android.content.res.Resources
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework13.BaseFragment
import com.example.homework13.R
import com.example.homework13.databinding.FieldItemBinding
import com.example.homework13.databinding.FragmentFieldBinding
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset

class FieldFragment : BaseFragment<FragmentFieldBinding>(FragmentFieldBinding::inflate) {
    private var fields = mutableListOf<Field>()
    private val viewModel: FieldViewModel by viewModels()
    private val adapter: FiledAdapter by lazy {
        FiledAdapter()
    }
    override fun setup() {
        getData()
        with(binding){
            fieldsRecyclerView.layoutManager = LinearLayoutManager(context)
            fieldsRecyclerView.adapter = FiledAdapter().apply {
                submitList(fields)
            }
        }

        binding.btnRegister.setOnClickListener(){
            println("gozolaaaaa")
            adapter.FiledVH(binding = FieldItemBinding.inflate(layoutInflater)).emptyCheck()
        }
    }

    private fun getData(){
        val jsonFileString = readJsonFromResources(resources, R.raw.data)
        var dataList = JSONArray(jsonFileString)
        for (i in 0..<dataList.length()){
            val fItem: JSONObject = dataList.getJSONObject(i)
            fields.add(Field(fItem.getString("field_id").toInt(),fItem.getString("hint"),fItem.getString("field_type"),fItem.getString("is_active")))
        }
    }
}

private fun readJsonFromResources(resources: Resources, resourceId: Int): String {
    val inputStream: InputStream = resources.openRawResource(resourceId)
    val bufferedReader = BufferedReader(InputStreamReader(inputStream, Charset.forName("UTF-8")))
    val stringBuilder = StringBuilder()
    var line: String?

    try {
        while (bufferedReader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        try {
            inputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return stringBuilder.toString()
}

