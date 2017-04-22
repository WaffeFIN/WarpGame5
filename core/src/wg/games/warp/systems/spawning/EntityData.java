/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems.spawning;

import com.artemis.Archetype;
import com.artemis.Component;
import com.artemis.utils.Bag;

/**

 @author Walter
 */
class EntityData {
    
    final Archetype archetype;
    final Bag<Component> defaultComponentValues;
    
    public EntityData(Archetype archetype) {
        this.archetype = archetype;
        this.defaultComponentValues = new Bag<Component>();
    }

    /**
     Adds the component c to the default component values. When an entity is
     created the values stored in the components are copied over to the new
     entity.

     @param c the component (to add) with the entity's default values
     @return the EntityData instance for easy chaining.
     */
    public EntityData add(Component c) {
        defaultComponentValues.add(c);
        return this;
    }
    
}
