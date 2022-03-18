package com.sk.cloudstorage.utils.log

import com.sk.skextension.kotlin.emptyOutputStream
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File

object CommonLogUtil {
    var log: Logger
    val logFilesPath = "/sdcard/logs"
    init {
        log = LoggerFactory.getLogger(this::class.java)
    }

    /**
     * 上传日志
     */
    fun getUploadLogFile(date: String): File {
        val logFiles = File(logFilesPath).listFiles().toMutableList().stream().filter {
            it.name.contains(date)
        }
        val uploadFile = File("${logFilesPath}/upload.log")
        if (!uploadFile.exists()) {
            uploadFile.createNewFile()
        }
        val fos = uploadFile.emptyOutputStream()
        logFiles.forEach {
            val fis = it.inputStream()
            fos.write(fis.readBytes())
            fis.close()
        }
        fos.close()
        return uploadFile
    }
}