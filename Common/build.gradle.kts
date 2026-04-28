plugins {
    id("fuzs.multiloader.multiloader-convention-plugins-common")
}

dependencies {
    modCompileOnlyApi(sharedLibs.puzzleslib.common)
}

multiloader {
    mixins {
        clientMixin("ClientPacketListenerMixin", "GuiItemRenderStateMixin", "GuiRendererMixin", "GuiRenderStateMixin")
    }
}
