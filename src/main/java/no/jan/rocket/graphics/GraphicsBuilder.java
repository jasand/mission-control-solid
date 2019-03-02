package no.jan.rocket.graphics;

import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

public class GraphicsBuilder {

    public static MeshView createRocketMeshView() {
        Image texImage = new Image((new GraphicsBuilder()).getClass().getResourceAsStream("/images/triangle_texmap.png"));
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(texImage);
        material.setSelfIlluminationMap(texImage);
//        material.setSpecularColor(Color.WHITE);

        float points[] =
                {
                        // Nosecone
                        0,  0, -25, // 0
                        -3,  1,  -17,
                        -1,  3,  -17,
                        1,  3,  -17,
                        3,  1,  -17,
                        3, -1,  -17, // 5
                        1, -3,  -17,
                        -1, -3,  -17,
                        -3, -1,  -17,

                        // Øverste seksjon
                        -3,  1,  -9,
                        -1,  3,  -9, // 10
                        1,  3,  -9,
                        3,  1,  -9,
                        3, -1,  -9,
                        1, -3,  -9,
                        -1, -3,  -9, // 15
                        -3, -1,  -9,

                        // Seksjon med noe sort...
                        -3,  1,  0,
                        -1,  3,  0,
                        1,  3,  0,
                        3,  1,  0,   // 20
                        3, -1,  0,
                        1, -3,  0,
                        -1, -3, 0,
                        -3, -1, 0,

                        // Bunnseksjon
                        -3,  1,  25,  // 25
                        -1,  3,  25,
                        1,  3,  25,
                        3,  1,  25,
                        3, -1,  25,
                        1, -3,  25,   // 30
                        -1, -3, 25,
                        -3, -1, 25,

                        // Halefinner
                        // 1
                        -2, 2, 17,
                        -5, 5, 25,
                        -2, 2, 25,    // 35
                        // 2
                        2, 2, 17,
                        5, 5, 25,
                        2, 2, 25,
                        // 3
                        2, -2, 17,
                        5, -5, 25,   // 40
                        2, -2, 25,
                        // 4
                        -2, -2, 17,
                        -5, -5, 25,
                        -2, -2, 25,

                        // Bottom
                        0,  0, 25, // 45
                        -3,  1, 25,
                        -1,  3, 25,
                        1,  3, 25,
                        3,  1, 25,
                        3, -1, 25, // 50
                        1, -3, 25,
                        -1, -3, 25,
                        -3, -1, 25

                };

        float tex[] =
                {
                        // hvit nosecone
                        0.5f, 0.35f,
                        0.48f, 0.45f,
                        0.52f, 0.45f,

                        // Hvit
                        0.1f,  0.1f,  // 3
                        0.1f, 0.3f,
                        0.2f, 0.3f,
                        0.2f, 0.1f,

                        // Sort triangel
                        0.45f,  0.5f,  // 7
                        0.45f, 0.7f,
                        0.55f, 0.7f,
                        0.55f, 0.5f


                };

        int faces[] =
                {
                        // Nosecone
                        0, 0, 1, 1, 2, 2,
                        0, 0, 2, 1, 3, 2,
                        0, 0, 3, 1, 4, 2,
                        0, 0, 4, 1, 5, 2,
                        0, 0, 5, 1, 6, 2,
                        0, 0, 6, 1, 7, 2,
                        0, 0, 7, 1, 8, 2,
                        0, 0, 8, 1, 1, 2,

                        // Section 1
                        1, 3,  9, 4, 10, 5,
                        1, 4, 10, 5,  2, 6,

                        2, 3, 10, 4, 11, 5,
                        2, 4, 11, 5,  3, 6,

                        3, 3, 11, 4, 12, 5,
                        3, 4, 12, 5,  4, 6,

                        4, 3, 12, 4, 13, 5,
                        4, 4, 13, 5,  5, 6,

                        5, 3, 13, 4, 14, 5,
                        5, 4, 14, 5,  6, 6,

                        6, 3, 14, 4, 15, 5,
                        6, 4, 15, 5,  7, 6,

                        7, 3, 15, 4, 16, 5,
                        7, 4, 16, 5,  8, 6,

                        8, 3, 16, 4, 9, 5,
                        8, 4,  9, 5, 1, 6,

                        // Section 2 - Med litt sort foran...
                        9, 7, 17, 8, 18, 9,
                        9, 7, 18, 9, 10, 10,

                        10, 7, 18, 8, 19, 9,
                        10, 7, 19, 9, 11, 10,

                        11, 7, 19, 8, 20, 9,
                        11, 7, 20, 9, 12, 10,

                        12, 3, 20, 4, 21, 5,
                        12, 3, 21, 4, 13, 5,

                        13, 3, 21, 4, 22, 5,
                        13, 3, 22, 4, 14, 5,

                        14, 3, 22, 4, 23, 5,
                        14, 3, 23, 4, 15, 5,

                        15, 3, 23, 4, 24, 5,
                        15, 3, 24, 4, 16, 5,

                        16, 3, 24, 4, 17, 5,
                        16, 3, 17, 4,  9, 5,

                        // Section 3 - Bunnseksjon...
                        17, 3, 25, 4, 26, 5,
                        17, 3, 26, 4, 18, 5,

                        18, 3, 26, 4, 27, 5,
                        18, 3, 27, 4, 19, 5,

                        19, 3, 27, 4, 28, 5,
                        19, 3, 28, 4, 20, 5,

                        20, 3, 28, 4, 29, 5,
                        20, 3, 29, 4, 21, 5,

                        21, 3, 29, 4, 30, 5,
                        21, 3, 30, 4, 22, 5,

                        22, 3, 30, 4, 31, 5,
                        22, 3, 31, 4, 23, 5,

                        23, 3, 31, 4, 32, 5,
                        23, 3, 32, 4, 24, 5,

                        24, 3, 32, 4, 25, 5,
                        24, 3, 25, 4, 17, 5,

                        // Tailfins
                        33, 10, 34, 8, 35, 9,
                        33,  6, 35, 4, 34, 5,

                        36, 7, 38, 9, 37, 8,
                        36, 6, 37, 4, 38, 5,

                        39, 10, 41, 8, 40, 9,
                        39,  6, 40, 4, 41, 5,

                        42, 10, 43, 8, 44, 9,
                        42,  6, 44, 4, 43, 5,

                        // Nosecone
                        45, 3, 47, 5, 46, 4,
                        45, 3, 48, 5, 47, 4,
                        45, 3, 49, 5, 48, 4,
                        45, 3, 50, 5, 49, 4,
                        45, 3, 51, 5, 50, 4,
                        45, 3, 52, 5, 51, 4,
                        45, 3, 53, 5, 52, 4,
                        45, 3, 46, 5, 53, 4
                };

        TriangleMesh mesh = new TriangleMesh();
        mesh.getPoints().addAll(points);
        mesh.getTexCoords().addAll(tex);
        mesh.getFaces().addAll(faces);

        MeshView rocket = new MeshView(mesh);
        //box.setDrawMode(DrawMode.FILL);
        rocket.setMaterial(material);
        return rocket;
    }
}
