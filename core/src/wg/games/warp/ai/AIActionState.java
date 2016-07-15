/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.ai;

/**
 * REMOVE THIS
 * @author Walter
 */
public enum AIActionState {

    /**
     * Null state.
     */
    NULL,
    /**
     * Sleeping. Stays asleep until woken up.
     */
    SLEEPING,
    /**
     * Short state after SLEEPING.
     */
    WAKING_UP,
    /**
     * Stands still.
     */
    WAITING,
    /**
     * Follows a specific path. When meeting a dead end, the AI turns around and
     * continues back on the same path (as long as possible). If no
     * pre-determined path is given, the AI will patrol within one node cluster.
     */
    PATROLLING,
    /**
     * Casually moving around world. Mainly used for Crawlers.
     */
    WANDERING,
    /**
     * Moving towards player.
     */
    AGGRESSION,
    /**
     * Moving towards player using alternative path finding e.g. assassin's
     * phase shift.
     */
    AGGRESSION_ALTERNATIVE,
    /**
     * Reloading animation. Used by default attack
     */
    RELOADING,
    /**
     * Launching the default attack towards player.
     */
    ATTACK,
    /**
     * Charging attack or special attack.
     */
    CHARGE_FOR_SPECIAL,
    /**
     * Launching the special attack towards player.
     */
    SPECIAL_ATTACK,
    /**
     * Cooldown after launching special attack.
     */
    SPECIAL_COOLDOWN,
    /**
     * Short hit animation. When hit, the AI will not turn dazed.
     */
    HIT,
    /**
     * Hit animation. 
     */
    HARD_HIT,
    /**
     * Stunned and (usually) upside down.
     */
    DAZED,
    /**
     * State after dazed. Used during recovery animation
     */
    RECOVERING,
    /**
     * Dying/being defeated animation.
     */
    DEFEATED,
    /**
     * Dead. Should be turned into a background entity here.
     */
    DEAD;
}
