package TextEditorJB.Entities

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class FileInfo {

    var openingFile : File? = null
    var readerPosition = 0
    var fileReader : FileReader? = null
    var buffer : BufferedReader? = null

}