package com.moxin.onnxruntime;

import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OnnxModelService {

    private final OrtEnvironment ortEnvironment;
    private final OrtSession ortSession;

    @Autowired
    public OnnxModelService(OrtEnvironment ortEnvironment, OrtSession ortSession) {
        this.ortEnvironment = ortEnvironment;
        this.ortSession = ortSession;
    }

    public float[] runModel(float[] inputData) throws OrtException {
        long[] shape = new long[]{1, inputData.length};
        OnnxTensor inputTensor = OnnxTensor.createTensor(ortEnvironment, FloatBuffer.wrap(inputData), shape);

        Map<String, OnnxTensor> inputs = new HashMap<>();
        String inputName = ortSession.getInputInfo().keySet().iterator().next();
        inputs.put(inputName, inputTensor);

        OrtSession.Result result = ortSession.run(inputs);

        OnnxTensor outputTensor = (OnnxTensor) result.get(0);
        FloatBuffer floatBuffer = outputTensor.getFloatBuffer();
        float[] outputData = new float[floatBuffer.remaining()];
        floatBuffer.get(outputData);

        inputTensor.close();
        outputTensor.close();

        return outputData;
    }


    public List<Float> runModel(List<Float> inputData) throws OrtException {
        float[] inputArray = new float[inputData.size()];
        for (int i = 0; i < inputData.size(); i++) {
            inputArray[i] = inputData.get(i);
        }

        long[] shape = new long[]{1, inputArray.length};
        OnnxTensor inputTensor = OnnxTensor.createTensor(ortEnvironment, FloatBuffer.wrap(inputArray), shape);

        Map<String, OnnxTensor> inputs = new HashMap<>();
        String inputName = ortSession.getInputInfo().keySet().iterator().next();
        inputs.put(inputName, inputTensor);

        OrtSession.Result result = ortSession.run(inputs);

        OnnxTensor outputTensor = (OnnxTensor) result.get(0);
        FloatBuffer floatBuffer = outputTensor.getFloatBuffer();
        float[] outputData = new float[floatBuffer.remaining()];
        floatBuffer.get(outputData);

        inputTensor.close();
        outputTensor.close();

        List<Float> outputList = new ArrayList<>();
        for (float value : outputData) {
            outputList.add(value);
        }

        return outputList;
    }


}
