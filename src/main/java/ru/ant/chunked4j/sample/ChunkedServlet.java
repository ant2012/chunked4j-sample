package ru.ant.chunked4j.sample;

import ru.ant.chunked4j.ChunkException;
import ru.ant.chunked4j.ChunkService;

import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

@WebServlet(name = "ChunkedServlet",urlPatterns = "/chunked/")
public class ChunkedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ChunkService.getInstance().putChunk(request);
        } catch (ChunkException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileId = request.getParameter("fileId");
        if(fileId != null){
            Serializable result = ChunkService.getInstance().getUploadResult(fileId);
            if(result != null){
                PrintWriter writer = response.getWriter();
                String resp = getJsonString((SimpleFileResult) result);
                writer.write(resp);
            }
        }
    }

    private String getJsonString(SimpleFileResult simpleFileResult) {
        return Json.createObjectBuilder()
                .add("filePath", simpleFileResult.getFilePath())
                .build().toString();
    }
}
