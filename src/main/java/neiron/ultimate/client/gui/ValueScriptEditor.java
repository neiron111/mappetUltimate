package neiron.ultimate.client.gui;

import mchorse.mappet.Mappet;
import mchorse.mappet.client.gui.scripts.GuiTextEditor;
import mchorse.mappet.client.gui.scripts.themes.Themes;
import mchorse.mappet.client.gui.scripts.utils.SyntaxStyle;
import mchorse.mappet.client.gui.utils.overlays.GuiEditorOverlayPanel;
import mchorse.mclib.client.gui.framework.elements.buttons.GuiIconElement;
import mchorse.mclib.client.gui.framework.elements.buttons.GuiToggleElement;
import mchorse.mclib.client.gui.framework.elements.context.GuiContextMenu;
import mchorse.mclib.client.gui.framework.elements.context.GuiSimpleContextMenu;
import mchorse.mclib.client.gui.framework.elements.input.GuiColorElement;
import mchorse.mclib.client.gui.framework.elements.input.GuiTextElement;
import mchorse.mclib.client.gui.framework.elements.list.GuiListElement;
import mchorse.mclib.client.gui.framework.elements.modals.GuiModal;
import mchorse.mclib.client.gui.framework.elements.modals.GuiPromptModal;
import mchorse.mclib.client.gui.utils.Icons;
import mchorse.mclib.client.gui.utils.keys.IKey;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;

public class ValueScriptEditor extends GuiEditorOverlayPanel<ValueScriptEditor.SyntaxStyleEntry> {



        public String sampleCode()
        {
            try {
                return String.join("\n", Files.readAllLines(Paths.get(".\\config\\mappetultimate").resolve("script.json"), StandardCharsets.UTF_8));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    public void setData() throws IOException {
        preview.getText();

        Files.write(Paths.get(".\\config\\mappetultimate").resolve("script.json"), preview.getText().getBytes(StandardCharsets.UTF_8));
    }


    public static GuiContextMenu createScriptContextMenu(Minecraft mc, GuiTextEditor editor)
    {
        /* These GUI QoL features are getting out of hand... */
        GuiSimpleContextMenu menu = new GuiSimpleContextMenu(mc)
                .action(Icons.CODE, IKey.lang("mappetultimate.config.Mappet-Ultimate.editor.context.return"), () -> handlerReturn(editor))
                .action(Icons.SAVED, IKey.lang("mappetultimate.config.Mappet-Ultimate.editor.context.save"), () -> handlerSave(editor));


        return menu;
    }

    private static void handlerReturn(GuiTextEditor editor)
    {
        preview.setText("function main(c)\n{\n    // Code...\n    var s = c.getSubject();\n}");
    }

    private static void handlerSave(GuiTextEditor editor)
    {
        preview.getText();

        try {
            Files.write(Paths.get(".\\config\\mappetultimate").resolve("script.json"), preview.getText().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public GuiIconElement open;

    public GuiTextElement title;
    public GuiToggleElement shadow;
    public GuiColorElement primary;
    public GuiColorElement secondary;
    public GuiColorElement identifier;
    public GuiColorElement special;
    public GuiColorElement strings;
    public GuiColorElement comments;
    public GuiColorElement numbers;
    public GuiColorElement other;
    public GuiColorElement lineNumbers;
    public GuiColorElement background;
    public static GuiTextEditor preview;

    public ValueScriptEditor(Minecraft mc) {
        super(mc, IKey.lang("mappetultimate.config.Mappet-Ultimate.editor.main"));

        this.open = new GuiIconElement(mc, Icons.SAVED, (b) -> {
            try {
                setData();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        this.open.tooltip(IKey.lang("mappetultimate.config.Mappet-Ultimate.editor.save")).flex().wh(16, 16);

        this.title = new GuiTextElement(mc, 100, (s) -> this.item.style.title = s);
        this.shadow = new GuiToggleElement(mc, IKey.lang("mappet.gui.syntax_theme.shadow"), (b) -> this.item.style.shadow = b.isToggled());
        this.primary = new GuiColorElement(mc, (c) ->
        {
            this.item.style.primary = c;
            this.preview.resetHighlight();
        });
        this.primary.tooltip(IKey.lang("mappet.gui.syntax_theme.colors.primary"));
        this.secondary = new GuiColorElement(mc, (c) ->
        {
            this.item.style.secondary = c;
            this.preview.resetHighlight();
        });
        this.secondary.tooltip(IKey.lang("mappet.gui.syntax_theme.colors.secondary"));
        this.identifier = new GuiColorElement(mc, (c) ->
        {
            this.item.style.identifier = c;
            this.preview.resetHighlight();
        });
        this.identifier.tooltip(IKey.lang("mappet.gui.syntax_theme.colors.identifier"));
        this.special = new GuiColorElement(mc, (c) ->
        {
            this.item.style.special = c;
            this.preview.resetHighlight();
        });
        this.special.tooltip(IKey.lang("mappet.gui.syntax_theme.colors.special"));
        this.strings = new GuiColorElement(mc, (c) ->
        {
            this.item.style.strings = c;
            this.preview.resetHighlight();
        });
        this.strings.tooltip(IKey.lang("mappet.gui.syntax_theme.colors.strings"));
        this.comments = new GuiColorElement(mc, (c) ->
        {
            this.item.style.comments = c;
            this.preview.resetHighlight();
        });
        this.comments.tooltip(IKey.lang("mappet.gui.syntax_theme.colors.comments"));
        this.numbers = new GuiColorElement(mc, (c) ->
        {
            this.item.style.numbers = c;
            this.preview.resetHighlight();
        });
        this.numbers.tooltip(IKey.lang("mappet.gui.syntax_theme.colors.numbers"));
        this.other = new GuiColorElement(mc, (c) ->
        {
            this.item.style.other = c;
            this.preview.resetHighlight();
        });
        this.other.tooltip(IKey.lang("mappet.gui.syntax_theme.colors.other"));
        this.lineNumbers = new GuiColorElement(mc, (c) -> this.item.style.lineNumbers = c);
        this.lineNumbers.tooltip(IKey.lang("mappet.gui.syntax_theme.background_colors.line_numbers"));
        this.background = new GuiColorElement(mc, (c) -> this.item.style.background = c);
        this.background.tooltip(IKey.lang("mappet.gui.syntax_theme.background_colors.background"));
        this.preview = new GuiTextEditor(mc, null);
        this.preview.background().context(() -> createScriptContextMenu(this.mc, this.preview));

        this.content.flex().h(0.5F);
        this.preview.flex().relative(this).y(0.0F, 28).w(1F).hTo(this.area, 1F);
        this.preview.setText(sampleCode());

        this.add(this.preview.background());
        this.icons.add(this.open);

    }


    @Override
    protected GuiListElement<SyntaxStyleEntry> createList(Minecraft mc)
    {
        return new GuiSyntaxStyleListElement(mc, (l) -> this.pickItem(l.get(0), false));
    }

    @Override
    protected IKey getAddLabel()
    {
        return IKey.lang("mappet.gui.syntax_theme.context.add");
    }

    @Override
    protected IKey getRemoveLabel()
    {
        return IKey.lang("mappet.gui.syntax_theme.context.remove");
    }

    @Override
    protected void addItem()
    {
        GuiModal.addFullModal(this, () -> new GuiPromptModal(this.mc, IKey.lang("mappet.gui.syntax_theme.modal.add"), this::addNewTheme));
    }

    private void addNewTheme(String string)
    {
        File file = Themes.themeFile(string);

        if (!file.isFile())
        {
            SyntaxStyle style = new SyntaxStyle();
            SyntaxStyleEntry entry = new SyntaxStyleEntry(file, style);

            style.title = "";
            this.list.add(entry);
            this.list.update();
            this.pickItem(entry, true);
        }
    }

    @Override
    protected void removeItem()
    {
        this.list.getCurrentFirst().file.delete();

        super.removeItem();
    }

    @Override
    protected void pickItem(SyntaxStyleEntry item, boolean select)
    {
        item.save();
        Mappet.scriptEditorSyntaxStyle.set(item.file.getName(), item.style);

        this.preview.getHighlighter().setStyle(item.style);
        this.preview.resetHighlight();

        super.pickItem(item, select);
    }

    @Override
    protected void fillData(SyntaxStyleEntry item)
    {


        this.title.setText(item.style.title);
        this.shadow.toggled(item.style.shadow);
        this.primary.picker.setColor(item.style.primary);
        this.secondary.picker.setColor(item.style.secondary);
        this.identifier.picker.setColor(item.style.identifier);
        this.special.picker.setColor(item.style.special);
        this.strings.picker.setColor(item.style.strings);
        this.comments.picker.setColor(item.style.comments);
        this.numbers.picker.setColor(item.style.numbers);
        this.lineNumbers.picker.setColor(item.style.lineNumbers);
        this.background.picker.setColor(item.style.background);
        this.other.picker.setColor(item.style.other);
    }

    @Override
    public void onClose()
    {

        super.onClose();
    }

    public static class GuiSyntaxStyleListElement extends GuiListElement<SyntaxStyleEntry>
    {
        public GuiSyntaxStyleListElement(Minecraft mc, Consumer<List<SyntaxStyleEntry>> callback)
        {
            super(mc, callback);

            this.scroll.scrollItemSize = 16;
        }

        @Override
        protected String elementToString(SyntaxStyleEntry element)
        {
            if (element.style.title.trim().isEmpty())
            {
                return element.file.getName();
            }

            return element.style.title + " (" + element.file.getName() + ")";
        }
    }

    public static class SyntaxStyleEntry
    {
        public File file;
        public SyntaxStyle style;

        public SyntaxStyleEntry(File file, SyntaxStyle style)
        {
            this.file = file;
            this.style = style;
        }

        public void save()
        {
            Themes.writeTheme(this.file, this.style);
        }
    }
}
