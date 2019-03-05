package com.hari.recyclerviews.recyclerAnimations

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import java.util.*

class SlideInAnimator() : SimpleItemAnimator() {

    constructor(interpolator: TimeInterpolator) : this() {
        this.sDefaultInterpolator = interpolator
    }

    private var sDefaultInterpolator: TimeInterpolator? = null
    private val mPendingAdditions = ArrayList<RecyclerView.ViewHolder>()
    private var mAdditionsList = ArrayList<ArrayList<RecyclerView.ViewHolder>>()

    internal var mAddAnimations = ArrayList<RecyclerView.ViewHolder>()

    override fun animateAdd(holder: RecyclerView.ViewHolder?): Boolean {
        if (holder != null) {
            resetAnimation(holder)


            holder.itemView.translationX = (-holder.itemView.width).toFloat()

            mPendingAdditions.add(holder)
        }
        return true
    }


    private fun animateAddImpl(holder: RecyclerView.ViewHolder) {
        val view = holder.itemView
        val animation = view.animate()
        mAddAnimations.add(holder)
        animation.translationX(0F).setDuration(addDuration).setInterpolator(sDefaultInterpolator)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animator: Animator) {
                    dispatchAddStarting(holder)
                }

                override fun onAnimationCancel(animator: Animator) {
                    view.translationX = 0F
                }

                override fun onAnimationEnd(animator: Animator) {
                    animation.setListener(null)
                    dispatchAddFinished(holder)
                    mAddAnimations.remove(holder)
                    dispatchFinishedWhenDone()
                }
            }).start()
    }

    override fun runPendingAnimations() {
        val additionsPending = !mPendingAdditions.isEmpty()
        if (!additionsPending) return

        if (additionsPending) {
            val additions = ArrayList<RecyclerView.ViewHolder>()
            additions.addAll(mPendingAdditions)
            mAdditionsList.add(additions)
            mPendingAdditions.clear()
            val adder = Runnable {
                for (holder in additions) {
                    animateAddImpl(holder)
                }
                additions.clear()
                mAdditionsList.remove(additions)
            }
/*            if (removalsPending || movesPending || changesPending) {
                val removeDuration = if (removalsPending) removeDuration else 0
                val moveDuration = if (movesPending) moveDuration else 0
                val changeDuration = if (changesPending) changeDuration else 0
                val totalDelay = removeDuration + Math.max(moveDuration, changeDuration)
                val view = additions[0].itemView
                ViewCompat.postOnAnimationDelayed(view, adder, totalDelay)
            } else {*/
            adder.run()
//            }
        }

    }

    override fun animateMove(holder: RecyclerView.ViewHolder?, fromX: Int, fromY: Int, toX: Int, toY: Int): Boolean {
        dispatchMoveFinished(holder)
        return true
    }

    override fun animateChange(
        oldHolder: RecyclerView.ViewHolder?,
        newHolder: RecyclerView.ViewHolder?,
        fromLeft: Int,
        fromTop: Int,
        toLeft: Int,
        toTop: Int
    ): Boolean {
        dispatchChangeFinished(newHolder, true)
        return true
    }

    override fun isRunning(): Boolean {
        return (!mPendingAdditions.isEmpty()
                || !mAddAnimations.isEmpty()
                || !mAdditionsList.isEmpty())

    }

    override fun endAnimation(item: RecyclerView.ViewHolder) {

    }

    override fun animateRemove(holder: RecyclerView.ViewHolder?): Boolean {
        dispatchRemoveFinished(holder)
        return true
    }

    override fun endAnimations() {

    }

    private fun resetAnimation(holder: RecyclerView.ViewHolder) {
        if (sDefaultInterpolator == null) {
            sDefaultInterpolator = ValueAnimator().interpolator
        }
        holder.itemView.animate().interpolator = sDefaultInterpolator
        endAnimation(holder)
    }

    internal fun dispatchFinishedWhenDone() {
        if (!isRunning) {
            dispatchAnimationsFinished()
        }
    }

}