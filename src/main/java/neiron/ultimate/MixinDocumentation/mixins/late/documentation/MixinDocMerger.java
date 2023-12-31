package neiron.ultimate.MixinDocumentation.mixins.late.documentation;

import com.google.gson.Gson;
import mchorse.mappet.client.gui.scripts.GuiDocumentationOverlayPanel;
import mchorse.mappet.client.gui.scripts.utils.documentation.DocClass;
import mchorse.mappet.client.gui.scripts.utils.documentation.DocMerger;
import mchorse.mappet.client.gui.scripts.utils.documentation.DocMethod;
import mchorse.mappet.client.gui.scripts.utils.documentation.Docs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.Language;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import neiron.ultimate.MixinDocumentation.mixins.utils.PreciseDocClass ;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Mixin(value = DocMerger.class, remap = false)
public abstract class MixinDocMerger {

    @Inject(
            method = "getMergedDocs",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lcom/google/gson/Gson;fromJson(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;"),
            remap = false,
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private static void addDocs(CallbackInfoReturnable<Docs> cir, Minecraft mc, Language lg, Gson gson, List<Docs> docsList) {
        InputStream stream = GuiDocumentationOverlayPanel.class.getResourceAsStream("/assets/ultimate/docs.json");
        Scanner scanner = new Scanner(stream, "UTF-8");
        Docs MappetUltimateDocs = gson.fromJson(scanner.useDelimiter("\\A").next(), Docs.class);
        MappetUltimateDocs.source = "MappetUltimate";
        MappetUltimateDocs.classes.forEach(clazz -> {
            clazz.source = MappetUltimateDocs.source;
            clazz.methods.forEach(method -> method.source = MappetUltimateDocs.source);
        });
        docsList.add(MappetUltimateDocs);
    }

    @Redirect(method = "mergeDocs",
            at = @At(
                    value = "FIELD",
                    target = "Lmchorse/mappet/client/gui/scripts/utils/documentation/DocClass;doc:Ljava/lang/String;",
                    opcode = Opcodes.PUTFIELD),
            remap = false
    )
    private static void mergeDocs(DocClass classMain, String input) {
        classMain.doc = input.trim().isEmpty() ? classMain.doc : input.trim();
    }

    @Inject(method = "mergeClasses", at = @At(value = "HEAD"), remap = false, cancellable = true)
    private static void mergeClasses(DocClass classMain, DocClass classAdd, CallbackInfo ci) {
        for (DocMethod methodAdd : classAdd.methods) {
            methodAdd.source = classAdd.source;

            DocMethod methodMain = ((PreciseDocClass) classMain).getExactMethod(methodAdd.getName());

            if (methodMain == null) {
                classMain.methods.add(methodAdd);
            } else {
                methodMain.doc = methodAdd.doc;
                Collections.replaceAll(classMain.methods, methodMain, methodAdd);
            }
        }
        ci.cancel();
    }
}
