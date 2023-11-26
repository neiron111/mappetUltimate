package neiron.ultimate.scripting.client;

import net.minecraft.nbt.NBTTagCompound;

public enum ClientData {
    DATE {
        public Object process(NBTTagCompound data) {
            return data.getString(this.name());
        }
    },

    SHADER {
        public Object process(NBTTagCompound data) {
            return data.getString(this.name());
        }
    },

    SCREEN {
        public Object process(NBTTagCompound data) {
            return data.getString(this.name());
        }
    };

    ClientData() {

    }

    public abstract Object process(NBTTagCompound data);

    private Object converter(String setting) {
        try {
            return Integer.parseInt(setting);
        } catch (NumberFormatException e) {
            try {
                return Double.parseDouble(setting);
            } catch (NumberFormatException g) {
                try {
                    return Float.parseFloat(setting);
                } catch (NumberFormatException f) {
                    try {
                        return Boolean.parseBoolean(setting);
                    } catch (NumberFormatException x) {
                        return setting;
                    }
                }
            }
        }
    }
}