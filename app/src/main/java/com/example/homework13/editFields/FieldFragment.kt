package com.example.homework13.editFields

import android.content.res.Resources
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework13.BaseFragment
import com.example.homework13.R
import com.example.homework13.databinding.FragmentFieldBinding
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset


class FieldFragment : BaseFragment<FragmentFieldBinding>(FragmentFieldBinding::inflate) {
    private var fields = mutableListOf<Field>()
    override fun setup() {
        getData()
    }

    private fun getData(){

        val jsonFileString = readJsonFromResources(resources, R.raw.data)
        var dataList = JSONArray(jsonFileString)
        println(dataList)
        for (i in 0..<dataList.length() -1){
            val fItem: JSONObject = dataList.getJSONObject(i)
//            val id = fItem.getInt("field_id")
//            val hint = fItem.getString("hint")
//            val inType = fItem.getString("field_type")
//            val isActive = fItem.getString("is_active")

            fields.add(Field(fItem.getString("field_id").toInt(),fItem.getString("hint"),fItem.getString("field_type"),fItem.getString("is_active")))
        }
        binding.fieldsRecyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.fieldsRecyclerView.adapter = FiledAdapter().apply {
            submitList(fields)
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

