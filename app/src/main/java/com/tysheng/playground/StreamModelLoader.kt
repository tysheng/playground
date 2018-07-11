package com.tysheng.playground

import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.data.DataFetcher
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import com.bumptech.glide.signature.ObjectKey
import java.io.IOException
import java.io.InputStream

/**
 * Created by tysheng
 * Date: 2/5/18 7:08 PM.
 * Email: tyshengsx@gmail.com
 */
class StreamModelLoader : ModelLoader<InputStream, InputStream> {


    override fun buildLoadData(model: InputStream, width: Int, height: Int, options: Options): ModelLoader.LoadData<InputStream>? {
        return ModelLoader.LoadData<InputStream>(ObjectKey(model), StreamDataFetcher(model))
    }

    override fun handles(model: InputStream): Boolean {
        return model != null
    }

}

class StreamDataFetcher(private val inputStream: InputStream) : DataFetcher<InputStream> {


    override fun getDataClass(): Class<InputStream> {
        return inputStream.javaClass
    }

    override fun cleanup() {
        try {
            inputStream.close()
        }
        catch (io: IOException) {

        }
    }

    override fun getDataSource(): DataSource {
        return DataSource.REMOTE
    }

    override fun cancel() {
    }

    override fun loadData(priority: Priority, callback: DataFetcher.DataCallback<in InputStream>) {
        callback?.onDataReady(inputStream)
    }

}

class StreamFactory: ModelLoaderFactory<InputStream, InputStream> {
    override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<InputStream, InputStream> {
        return StreamModelLoader()
    }

    override fun teardown() {
    }

}